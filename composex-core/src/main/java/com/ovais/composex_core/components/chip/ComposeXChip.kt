package com.ovais.composex_core.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor
import com.ovais.composex_core.core.configuration.toDp
import com.ovais.composex_core.core.configuration.toShape

/**
 * ComposeX Chip component following the design system
 *
 * @param text Chip text
 * @param onClick Optional click callback
 * @param modifier Modifier for the chip
 * @param variant Component variant
 * @param sizeToken Size token
 * @param enabled Whether the chip is enabled
 * @param selected Whether the chip is selected
 * @param onSelectionChange Callback when selection changes (makes chip toggleable)
 * @param leadingIcon Leading icon drawable resource (optional)
 * @param trailingIcon Trailing icon drawable resource (optional)
 * @param onDismiss Optional dismiss callback (makes it a dismissible chip)
 */
@Composable
fun ComposeXChip(
    text: String,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    enabled: Boolean = true,
    selected: Boolean = false,
    onSelectionChange: ((Boolean) -> Unit)? = null,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onDismiss: (() -> Unit)? = null
) {
    val state = when {
        !enabled -> ComponentState.Disabled
        selected -> ComponentState.Focused
        else -> ComponentState.Default
    }
    val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

    val backgroundColor = style.backgroundColor.toColor()
    val contentColor = style.contentColor.toColor()
    val shape = style.shape.toShape
    val padding = style.spacing.toDp * 0.5f
    val height = when (sizeToken) {
        SizeToken.Small -> 24.dp
        SizeToken.Medium -> 32.dp
        SizeToken.Large -> 40.dp
        SizeToken.ExtraLarge -> 48.dp
        is SizeToken.Custom -> sizeToken.size
    }

    val chipModifier = modifier
        .height(height)
        .clip(shape)
        .then(
            if (onClick != null || onSelectionChange != null || onDismiss != null) {
                Modifier.clickable(
                    enabled = enabled && (onClick != null || onSelectionChange != null),
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { 
                        if (onSelectionChange != null) {
                            onSelectionChange(!selected)
                        } else {
                            onClick?.invoke()
                        }
                    }
                )
            } else Modifier
        )

    val finalBackgroundColor = if (selected) {
        backgroundColor
    } else if (state == ComponentState.Disabled) {
        ColorType.Disabled().toColor()
    } else {
        backgroundColor.copy(alpha = 0.1f)
    }
    
    val borderWidth = if (selected) 2.dp else 0.dp
    val borderColor = if (selected && state != ComponentState.Disabled) {
        backgroundColor
    } else null
    
    Box(
        modifier = chipModifier
            .background(color = finalBackgroundColor)
            .then(
                if (borderColor != null) {
                    Modifier.border(BorderStroke(borderWidth, borderColor), shape)
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = padding,
                vertical = padding * 0.5f
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = if (state == ComponentState.Disabled) {
                        ColorType.Disabled(isLight = false).toColor()
                    } else contentColor
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
            Text(
                text = text,
                style = TextStyle(),
                color = if (state == ComponentState.Disabled) {
                    ColorType.Disabled(isLight = false).toColor()
                } else contentColor
            )
            trailingIcon?.let {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "Dismiss",
                    modifier = Modifier
                        .size(16.dp)
                        .then(
                            if (onDismiss != null) {
                                Modifier.clickable { onDismiss() }
                            } else Modifier
                        ),
                    tint = if (state == ComponentState.Disabled) {
                        ColorType.Disabled(isLight = false).toColor()
                    } else contentColor
                )
            }
        }
    }
}

