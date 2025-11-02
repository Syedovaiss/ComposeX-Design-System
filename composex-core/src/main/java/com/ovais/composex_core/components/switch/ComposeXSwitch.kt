package com.ovais.composex_core.components.switch

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor

/**
 * ComposeX Switch component following the design system
 *
 * @param checked Whether the switch is checked
 * @param onCheckedChange Callback when checked state changes
 * @param modifier Modifier for the switch
 * @param enabled Whether the switch is enabled
 * @param label Optional label text
 * @param variant Component variant for the switch
 * @param sizeToken Size token
 */
@Composable
fun ComposeXSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium
) {
    val state = if (enabled) ComponentState.Default else ComponentState.Disabled
    val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

    val checkedThumbColor = style.contentColor.toColor()
    val checkedTrackColor = style.backgroundColor.toColor()
    val uncheckedThumbColor = ColorType.Custom(Color.White).toColor()
    val uncheckedTrackColor = if (state == ComponentState.Disabled) {
        ColorType.Disabled().toColor()
    } else {
        checkedTrackColor.copy(alpha = 0.3f)
    }
    
    val disabledThumbColor = if (checked) {
        checkedThumbColor.copy(alpha = 0.6f)
    } else {
        uncheckedThumbColor.copy(alpha = 0.8f)
    }
    
    val disabledTrackColor = if (checked) {
        checkedTrackColor.copy(alpha = 0.5f)
    } else {
        uncheckedTrackColor.copy(alpha = 0.5f)
    }

    val colors: SwitchColors = SwitchDefaults.colors(
        checkedThumbColor = checkedThumbColor,
        checkedTrackColor = checkedTrackColor,
        uncheckedThumbColor = uncheckedThumbColor,
        uncheckedTrackColor = uncheckedTrackColor,
        disabledCheckedThumbColor = disabledThumbColor,
        disabledCheckedTrackColor = disabledTrackColor,
        disabledUncheckedThumbColor = disabledThumbColor,
        disabledUncheckedTrackColor = disabledTrackColor
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
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

