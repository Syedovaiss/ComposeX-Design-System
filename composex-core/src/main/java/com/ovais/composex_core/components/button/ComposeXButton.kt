package com.ovais.composex_core.components.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
 * ComposeX Filled Button component following the design system
 * This is the default elevated button with solid background
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
fun ComposeXButton(
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

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = params.shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = params.backgroundColor,
            contentColor = params.contentColor,
            disabledContainerColor = ColorType.Disabled().toColor(),
            disabledContentColor = ColorType.Disabled(isLight = false).toColor()
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = params.elevation,
            pressedElevation = params.elevation,
            disabledElevation = 0.dp
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
