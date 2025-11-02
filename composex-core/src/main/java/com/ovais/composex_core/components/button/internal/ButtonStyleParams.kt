package com.ovais.composex_core.components.button.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor
import com.ovais.composex_core.core.configuration.toDp
import com.ovais.composex_core.core.configuration.toShape

/**
 * Internal data class for button style parameters
 */
internal data class ButtonStyleParams(
    val backgroundColor: Color,
    val contentColor: Color,
    val borderColor: Color?,
    val shape: Shape,
    val elevation: Dp,
    val padding: Dp,
    val height: Dp
) {
    companion object {
        @Composable
        fun create(
            variant: ComponentVariant,
            sizeToken: SizeToken,
            enabled: Boolean
        ): ButtonStyleParams {
            val state = if (enabled) ComponentState.Default else ComponentState.Disabled
            val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

            val backgroundColor = style.backgroundColor.toColor()
            val contentColor = style.contentColor.toColor()
            val borderColor = style.borderColor?.toColor()
            val shape = remember(style.shape) { style.shape.toShape }
            val elevation = remember(style.elevation) { style.elevation.toDp }
            val padding = remember(style.spacing) { style.spacing.toDp }
            val height = remember(sizeToken) {
                when (sizeToken) {
                    SizeToken.Small -> 32.dp
                    SizeToken.Medium -> 40.dp
                    SizeToken.Large -> 48.dp
                    SizeToken.ExtraLarge -> 56.dp
                    is SizeToken.Custom -> sizeToken.size
                }
            }

            return ButtonStyleParams(
                backgroundColor = backgroundColor,
                contentColor = contentColor,
                borderColor = borderColor,
                shape = shape,
                elevation = elevation,
                padding = padding,
                height = height
            )
        }
    }
}

