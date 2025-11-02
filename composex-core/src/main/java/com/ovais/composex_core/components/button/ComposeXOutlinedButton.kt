package com.ovais.composex_core.components.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.button.internal.ButtonContent
import com.ovais.composex_core.components.button.internal.ButtonStyleParams
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor

/**
 * ComposeX Outlined Button component following the design system
 * Button with outline border and transparent background
 *
 * @param text Button text
 * @param onClick Callback when button is clicked
 * @param modifier Modifier for the button
 * @param variant Component variant (Primary, Secondary, etc.)
 * @param sizeToken Size token for the button
 * @param enabled Whether the button is enabled
 * @param icon Leading icon drawable resource (optional)
 * @param textStyle Optional text style override
 */
@Composable
fun ComposeXOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    enabled: Boolean = true,
    @DrawableRes icon: Int? = null,
    textStyle: TextStyle? = null
) {
    val params = ButtonStyleParams.create(
        variant = variant,
        sizeToken = sizeToken,
        enabled = enabled
    )

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = params.shape,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = params.contentColor,
            disabledContentColor = ColorType.Disabled(isLight = false).toColor()
        ),
        border = BorderStroke(
            width = 1.dp,
            color = params.borderColor ?: params.backgroundColor
        ),
        contentPadding = PaddingValues(
            horizontal = params.padding,
            vertical = params.padding * 0.5f
        ),
        interactionSource = remember { MutableInteractionSource() }
    ) {
        ButtonContent(
            text = text,
            icon = icon,
            textStyle = textStyle
        )
    }
}

