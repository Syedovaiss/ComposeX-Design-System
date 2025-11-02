package com.ovais.composex_core.components.builder

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentStyle
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.ElevationType
import com.ovais.composex_core.core.configuration.FontType
import com.ovais.composex_core.core.configuration.ShapeType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.SpacingType
import com.ovais.composex_core.core.configuration.toColor
import com.ovais.composex_core.core.theme.DisabledDark

@Composable
fun componentStyleOf(
    variant: ComponentVariant,
    state: ComponentState = ComponentState.Default,
    sizeToken: SizeToken = SizeToken.Medium
): ComponentStyle {

    val baseBackground = when (variant) {
        ComponentVariant.Primary -> ColorType.Info()
        ComponentVariant.Secondary -> ColorType.Info(isLight = false)
        ComponentVariant.Tertiary -> ColorType.Custom(DisabledDark)
        ComponentVariant.Success -> ColorType.Success()
        ComponentVariant.Danger -> ColorType.Danger()
        ComponentVariant.Warning -> ColorType.Warning()
        ComponentVariant.Info -> ColorType.Info()
        is ComponentVariant.Custom -> variant.backgroundColor
    }

    val baseContent = when (variant) {
        ComponentVariant.Tertiary -> ColorType.Custom(Color.Black)
        is ComponentVariant.Custom -> variant.contentColor
        else -> ColorType.Custom(Color.White)
    }

    val baseBorder = when (variant) {
        is ComponentVariant.Custom -> variant.borderColor
        else -> null
    }

    val baseElevation = when (variant) {
        is ComponentVariant.Custom -> variant.elevation
        else -> ElevationType.High
    }

    val baseShape = when (variant) {
        is ComponentVariant.Custom -> variant.shape
        else -> ShapeType.Medium
    }

    val backgroundColor = when (state) {
        ComponentState.Default -> baseBackground
        ComponentState.Pressed -> {
            val baseColor = baseBackground.toColor()
            ColorType.Custom(baseColor.copy(alpha = 0.85f))
        }
        ComponentState.Disabled -> ColorType.Disabled()
        ComponentState.Focused -> {
            val baseColor = baseBackground.toColor()
            ColorType.Custom(baseColor.copy(alpha = 0.9f))
        }
        ComponentState.Hovered -> {
            val baseColor = baseBackground.toColor()
            ColorType.Custom(baseColor.copy(alpha = 0.95f))
        }
    }

    val elevation = when (state) {
        ComponentState.Pressed -> ElevationType.High
        ComponentState.Disabled -> ElevationType.None
        else -> baseElevation
    }
    val fontType = when (sizeToken) {
        SizeToken.Small -> FontType.Caption
        SizeToken.Medium -> FontType.Body
        SizeToken.Large -> FontType.Title
        SizeToken.ExtraLarge -> FontType.Display
        is SizeToken.Custom -> FontType.Custom(sizeToken.size)
    }

    return ComponentStyle(
        variant = variant,
        state = state,
        backgroundColor = backgroundColor,
        contentColor = baseContent,
        borderColor = baseBorder,
        elevation = elevation,
        shape = baseShape,
        spacing = when (sizeToken) {
            SizeToken.Small -> SpacingType.Small
            SizeToken.Medium -> SpacingType.Medium
            SizeToken.Large -> SpacingType.Large
            SizeToken.ExtraLarge -> SpacingType.ExtraLarge
            is SizeToken.Custom -> SpacingType.Custom(sizeToken.size)
        },
        sizeToken = sizeToken,
        fontType = fontType
    )
}
