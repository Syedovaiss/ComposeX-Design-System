package com.ovais.composex_core.components.counter

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor
import com.ovais.composex_core.core.configuration.toDp
import com.ovais.composex_core.core.configuration.toShape

/**
 * ComposeX Counter component following the design system
 *
 * @param value Current counter value
 * @param onValueChange Callback when value changes
 * @param modifier Modifier for the counter
 * @param variant Component variant
 * @param sizeToken Size token
 * @param enabled Whether the counter is enabled
 * @param minValue Minimum value (default: 0)
 * @param maxValue Maximum value (default: Int.MAX_VALUE)
 * @param decreaseIcon Drawable resource for decrease button (optional, defaults to "−" text)
 * @param increaseIcon Drawable resource for increase button (optional, defaults to "+" text)
 */
@Composable
fun ComposeXCounter(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    enabled: Boolean = true,
    minValue: Int = 0,
    maxValue: Int = Int.MAX_VALUE,
    @DrawableRes decreaseIcon: Int? = null,
    @DrawableRes increaseIcon: Int? = null
) {
    val state = if (enabled) ComponentState.Default else ComponentState.Disabled
    val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

    val backgroundColor = style.backgroundColor.toColor()
    val contentColor = style.contentColor.toColor()
    val shape = style.shape.toShape
    val height = when (sizeToken) {
        SizeToken.Small -> 32.dp
        SizeToken.Medium -> 40.dp
        SizeToken.Large -> 48.dp
        SizeToken.ExtraLarge -> 56.dp
        is SizeToken.Custom -> sizeToken.size
    }

    val iconSize = when (sizeToken) {
        SizeToken.Small -> 16.dp
        SizeToken.Medium -> 20.dp
        SizeToken.Large -> 24.dp
        SizeToken.ExtraLarge -> 28.dp
        is SizeToken.Custom -> sizeToken.size * 0.5f
    }

    val padding = style.spacing.toDp * 0.25f

    Row(
        modifier = modifier
            .height(height)
            .clip(shape)
            .background(
                color = if (state == ComponentState.Disabled) {
                    ColorType.Disabled().toColor()
                } else backgroundColor
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Decrease button
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable(
                    enabled = enabled && value > minValue,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onValueChange((value - 1).coerceAtLeast(minValue)) }
                )
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (decreaseIcon != null) {
                Icon(
                    painter = painterResource(id = decreaseIcon),
                    contentDescription = "Decrease",
                    modifier = Modifier.size(iconSize),
                    tint = if (!enabled || value <= minValue) {
                        ColorType.Disabled(isLight = false).toColor()
                    } else contentColor
                )
            } else {
                Text(
                    text = "−",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    color = if (!enabled || value <= minValue) {
                        ColorType.Disabled(isLight = false).toColor()
                    } else contentColor
                )
            }
        }

        // Value display
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value.toString(),
                style = TextStyle(
                    fontWeight = FontWeight.Medium
                ),
                color = if (state == ComponentState.Disabled) {
                    ColorType.Disabled(isLight = false).toColor()
                } else contentColor
            )
        }

        // Increase button
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable(
                    enabled = enabled && value < maxValue,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onValueChange((value + 1).coerceAtMost(maxValue)) }
                )
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (increaseIcon != null) {
                Icon(
                    painter = painterResource(id = increaseIcon),
                    contentDescription = "Increase",
                    modifier = Modifier.size(iconSize),
                    tint = if (!enabled || value >= maxValue) {
                        ColorType.Disabled(isLight = false).toColor()
                    } else contentColor
                )
            } else {
                Text(
                    text = "+",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    color = if (!enabled || value >= maxValue) {
                        ColorType.Disabled(isLight = false).toColor()
                    } else contentColor
                )
            }
        }
    }
}

