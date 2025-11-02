package com.ovais.composex_core.components.checkbox

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
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
 * ComposeX Checkbox component following the design system
 *
 * @param checked Whether the checkbox is checked
 * @param onCheckedChange Callback when checked state changes
 * @param modifier Modifier for the checkbox
 * @param enabled Whether the checkbox is enabled
 * @param label Optional label text
 * @param variant Component variant for the checkbox
 * @param sizeToken Size token
 */
@Composable
fun ComposeXCheckbox(
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

    val checkedColor = style.backgroundColor.toColor()
    val uncheckedColor = if (state == ComponentState.Disabled) {
        ColorType.Disabled().toColor()
    } else {
        checkedColor.copy(alpha = 0.2f)
    }

    val checkboxSize = when (sizeToken) {
        SizeToken.Small -> 20.dp
        SizeToken.Medium -> 24.dp
        SizeToken.Large -> 28.dp
        SizeToken.ExtraLarge -> 32.dp
        is SizeToken.Custom -> sizeToken.size
    }

    val colors: CheckboxColors = CheckboxDefaults.colors(
        checkedColor = checkedColor,
        uncheckedColor = uncheckedColor,
        disabledCheckedColor = ColorType.Disabled().toColor(),
        disabledUncheckedColor = ColorType.Disabled().toColor(),
        checkmarkColor = style.contentColor.toColor()
    )

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            modifier = Modifier.size(checkboxSize),
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

