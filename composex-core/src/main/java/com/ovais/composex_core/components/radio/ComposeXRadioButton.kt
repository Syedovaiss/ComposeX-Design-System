package com.ovais.composex_core.components.radio

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor

/**
 * ComposeX RadioButton component following the design system
 *
 * @param selected Whether the radio button is selected
 * @param onClick Callback when radio button is clicked
 * @param modifier Modifier for the radio button
 * @param enabled Whether the radio button is enabled
 * @param label Optional label text
 * @param variant Component variant for the radio button
 * @param sizeToken Size token
 */
@Composable
fun ComposeXRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium
) {
    val state = if (enabled) ComponentState.Default else ComponentState.Disabled
    val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

    val selectedColor = style.backgroundColor.toColor()
    val unselectedColor = if (state == ComponentState.Disabled) {
        ColorType.Disabled().toColor()
    } else {
        selectedColor.copy(alpha = 0.3f)
    }

    val radioSize = when (sizeToken) {
        SizeToken.Small -> 20.dp
        SizeToken.Medium -> 24.dp
        SizeToken.Large -> 28.dp
        SizeToken.ExtraLarge -> 32.dp
        is SizeToken.Custom -> sizeToken.size
    }

    val colors: RadioButtonColors = RadioButtonDefaults.colors(
        selectedColor = selectedColor,
        unselectedColor = unselectedColor,
        disabledSelectedColor = ColorType.Disabled().toColor(),
        disabledUnselectedColor = ColorType.Disabled().toColor()
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.size(radioSize),
            colors = colors,
            interactionSource = remember { MutableInteractionSource() }
        )
        label?.let {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = it,
                style = TextStyle(),
                modifier = Modifier.padding(PaddingValues(vertical = 4.dp))
            )
        }
    }
}

