package com.ovais.composex_core.components.input

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor
import com.ovais.composex_core.core.configuration.toShape

/**
 * ComposeX TextField component following the design system
 *
 * @param value Current text value
 * @param onValueChange Callback when text changes
 * @param modifier Modifier for the text field
 * @param variant Component variant
 * @param sizeToken Size token
 * @param enabled Whether the field is enabled
 * @param label Optional label
 * @param placeholder Optional placeholder
 * @param leadingIcon Leading icon drawable resource (optional)
 * @param trailingIcon Trailing icon drawable resource (optional)
 * @param error Whether to show error state
 * @param supportingText Supporting text (for error messages, etc.)
 * @param visualTransformation Visual transformation (e.g., password masking)
 * @param keyboardOptions Keyboard options
 * @param keyboardActions Keyboard actions
 */
@Composable
fun ComposeXTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    placeholder: String? = null,
    @DrawableRes leadingIcon: Int? = null,
    @DrawableRes trailingIcon: Int? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    error: Boolean = false,
    supportingText: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    val state = when {
        !enabled -> ComponentState.Disabled
        error -> ComponentState.Focused
        else -> ComponentState.Default
    }
    val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

    val disabledColor = ColorType.Disabled().toColor()
    val dangerColor = ColorType.Danger().toColor()
    val backgroundColor = style.backgroundColor.toColor()
    val borderColorValue = style.borderColor?.toColor()

    val borderColor = when {
        !enabled -> disabledColor
        error -> dangerColor
        else -> borderColorValue ?: backgroundColor.copy(alpha = 0.5f)
    }

    val focusedBorderColor = if (error) {
        dangerColor
    } else {
        backgroundColor
    }

    val textColor = MaterialTheme.colorScheme.onSurface
    val disabledTextColorValue = ColorType.Disabled(isLight = false).toColor()

    val height = when (sizeToken) {
        SizeToken.Small -> 56.dp
        SizeToken.Medium -> 64.dp
        SizeToken.Large -> 72.dp
        SizeToken.ExtraLarge -> 80.dp
        is SizeToken.Custom -> sizeToken.size * 1.4f
    }

    val fontSize = when (sizeToken) {
        SizeToken.Small -> 14.sp
        SizeToken.Medium -> 16.sp
        SizeToken.Large -> 18.sp
        SizeToken.ExtraLarge -> 20.sp
        is SizeToken.Custom -> (sizeToken.size.value * 0.2f).sp
    }

    val disabledBorderColorValue = ColorType.Disabled().toColor()
    val focusedLabelColor = style.backgroundColor.toColor()
    val errorBorderColorValue = ColorType.Danger().toColor()

    val colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = textColor,
        unfocusedTextColor = textColor,
        disabledTextColor = disabledTextColorValue,
        focusedBorderColor = focusedBorderColor,
        unfocusedBorderColor = borderColor,
        disabledBorderColor = disabledBorderColorValue,
        focusedLabelColor = focusedLabelColor,
        unfocusedLabelColor = borderColor,
        disabledLabelColor = disabledBorderColorValue,
        errorBorderColor = errorBorderColorValue,
        errorLabelColor = errorBorderColorValue,
        errorSupportingTextColor = errorBorderColorValue
    )
    val leadingRes = leadingIcon
    val trailingRes = trailingIcon
    val supportingTxt = supportingText
    val verticalPadding = when (sizeToken) {
        SizeToken.Small -> 12.dp
        SizeToken.Medium -> 16.dp
        SizeToken.Large -> 20.dp
        SizeToken.ExtraLarge -> 24.dp
        is SizeToken.Custom -> sizeToken.size * 0.3f
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding),
        label = label,
        placeholder = placeholder?.let {
            {
                Text(it)
            }
        },

        leadingIcon = leadingRes?.let { res ->
            {
                Icon(
                    painter = painterResource(id = res),
                    contentDescription = null,
                    tint = borderColor
                )
            }
        },

        trailingIcon = trailingRes?.let { res ->
            {
                if (onTrailingIconClick != null) {
                    IconButton(onClick = onTrailingIconClick) {
                        Icon(
                            painter = painterResource(id = res),
                            contentDescription = null,
                            tint = borderColor
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(id = res),
                        contentDescription = null,
                        tint = borderColor
                    )
                }
            }
        },

        supportingText = supportingTxt?.let { txt ->
            { Text(text = txt) }
        },

        isError = error,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = colors,
        shape = style.shape.toShape,
        interactionSource = remember { MutableInteractionSource() },
        textStyle = TextStyle(fontSize = fontSize)
    )
}

