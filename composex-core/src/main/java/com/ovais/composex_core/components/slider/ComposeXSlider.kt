package com.ovais.composex_core.components.slider

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor

/**
 * ComposeX Slider component following the design system
 *
 * @param value Current slider value
 * @param onValueChange Callback when value changes
 * @param modifier Modifier for the slider
 * @param enabled Whether the slider is enabled
 * @param valueRange Range of values (default: 0f..1f)
 * @param steps Number of discrete steps (0 means continuous)
 * @param variant Component variant
 * @param sizeToken Size token
 */
@Composable
fun ComposeXSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium
) {
    val state = if (enabled) ComponentState.Default else ComponentState.Disabled
    val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

    val activeColor = style.backgroundColor.toColor()
    val inactiveColor = if (state == ComponentState.Disabled) {
        ColorType.Disabled().toColor()
    } else {
        activeColor.copy(alpha = 0.3f)
    }

    val thumbColor = style.contentColor.toColor()
    val disabledThumbColor = ColorType.Disabled().toColor()

    val colors: SliderColors = SliderDefaults.colors(
        thumbColor = if (enabled) thumbColor else disabledThumbColor,
        activeTrackColor = activeColor,
        inactiveTrackColor = inactiveColor,
        disabledThumbColor = disabledThumbColor,
        disabledActiveTrackColor = ColorType.Disabled().toColor(),
        disabledInactiveTrackColor = ColorType.Disabled().toColor()
    )

    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        colors = colors,
        interactionSource = remember { MutableInteractionSource() }
    )
}

