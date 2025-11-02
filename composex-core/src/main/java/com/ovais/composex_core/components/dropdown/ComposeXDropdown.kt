package com.ovais.composex_core.components.dropdown

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor
import com.ovais.composex_core.core.configuration.toDp
import com.ovais.composex_core.core.configuration.toShape

/**
 * Data class for dropdown menu items
 */
data class DropdownItem(
    val id: String,
    val text: String,
    val icon: Int? = null,
    val enabled: Boolean = true
)

/**
 * ComposeX Dropdown component following the design system
 *
 * @param selectedItem Currently selected item
 * @param items List of dropdown items
 * @param onItemSelected Callback when item is selected
 * @param modifier Modifier for the dropdown
 * @param variant Component variant
 * @param sizeToken Size token
 * @param enabled Whether the dropdown is enabled
 * @param placeholder Placeholder text when no item is selected
 * @param expanded Whether dropdown menu is expanded (for controlled usage)
 * @param onExpandedChange Callback when expanded state changes (for controlled usage)
 * @param arrowIcon Drawable resource for dropdown arrow icon (optional, defaults to text arrow)
 */
@Composable
fun ComposeXDropdown(
    selectedItem: DropdownItem?,
    items: List<DropdownItem>,
    onItemSelected: (DropdownItem) -> Unit,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    enabled: Boolean = true,
    placeholder: String = "Select an option",
    expanded: Boolean = false,
    onExpandedChange: ((Boolean) -> Unit)? = null,
    @DrawableRes arrowIcon: Int? = null,
    bordered: Boolean = true
) {
    var internalExpanded by remember { mutableStateOf(false) }
    val actualExpanded = if (onExpandedChange != null) expanded else internalExpanded
    val onExpandedChangeInternal = onExpandedChange ?: { internalExpanded = it }

    val state = if (enabled) ComponentState.Default else ComponentState.Disabled
    val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

    val contentColor = style.contentColor.toColor()
    val shape = style.shape.toShape
    val height = when (sizeToken) {
        SizeToken.Small -> 40.dp
        SizeToken.Medium -> 48.dp
        SizeToken.Large -> 56.dp
        SizeToken.ExtraLarge -> 64.dp
        is SizeToken.Custom -> sizeToken.size
    }
    val padding = style.spacing.toDp * 0.5f

    val backgroundColor = style.backgroundColor.toColor()
    val borderColorValue = style.borderColor?.toColor() ?: backgroundColor.copy(alpha = 0.5f)
    
    Box(modifier = modifier) {
        // Dropdown trigger
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(shape)
                .then(
                    if (bordered) {
                        Modifier.border(
                            BorderStroke(1.dp, borderColorValue),
                            shape
                        )
                    } else Modifier
                )
                .clickable(
                    enabled = enabled,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { onExpandedChangeInternal(!actualExpanded) },
                    indication = LocalIndication.current
                )
                .padding(horizontal = padding, vertical = padding * 0.5f),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    selectedItem?.icon?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = if (state == ComponentState.Disabled) {
                                ColorType.Disabled(isLight = false).toColor()
                            } else contentColor
                        )
                    }
                    Text(
                        text = selectedItem?.text ?: placeholder,
                        style = TextStyle(
                            fontSize = when (sizeToken) {
                                SizeToken.Small -> 14.sp
                                SizeToken.Medium -> 16.sp
                                SizeToken.Large -> 18.sp
                                SizeToken.ExtraLarge -> 20.sp
                                is SizeToken.Custom -> (sizeToken.size.value * 0.2f).sp
                            }
                        ),
                        color = when {
                            state == ComponentState.Disabled -> ColorType.Disabled(isLight = false).toColor()
                            selectedItem == null -> ColorType.Disabled(isLight = false).toColor().copy(alpha = 0.6f)
                            else -> contentColor
                        },
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
                // Arrow indicator
                arrowIcon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = if (actualExpanded) "Collapse" else "Expand",
                        modifier = Modifier.size(20.dp),
                        tint = if (state == ComponentState.Disabled) {
                            ColorType.Disabled(isLight = false).toColor()
                        } else contentColor
                    )
                } ?: Text(
                    text = if (actualExpanded) "▲" else "▼",
                    style = TextStyle(),
                    color = if (state == ComponentState.Disabled) {
                        ColorType.Disabled(isLight = false).toColor()
                    } else contentColor,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        // Dropdown menu
        DropdownMenu(
            expanded = actualExpanded,
            onDismissRequest = { onExpandedChangeInternal(false) },
            modifier = Modifier
                .clip(shape)
                .fillMaxWidth()
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item.icon?.let {
                                Icon(
                                    painter = painterResource(id = it),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = contentColor
                                )
                            }
                            Text(
                                text = item.text,
                                style = TextStyle()
                            )
                        }
                    },
                    onClick = {
                        onItemSelected(item)
                        onExpandedChangeInternal(false)
                    },
                    enabled = item.enabled
                )
            }
        }
    }
}

