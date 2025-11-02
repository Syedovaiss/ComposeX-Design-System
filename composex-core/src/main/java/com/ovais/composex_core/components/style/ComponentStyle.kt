package com.ovais.composex_core.components.style

import androidx.compose.ui.graphics.Color
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.ElevationType
import com.ovais.composex_core.core.configuration.FontType
import com.ovais.composex_core.core.configuration.ShapeType
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.SpacingType

data class ComponentStyle(
    val variant: ComponentVariant = ComponentVariant.Primary,
    val state: ComponentState = ComponentState.Default,
    val backgroundColor: ColorType = ColorType.Info(),
    val contentColor: ColorType = ColorType.Custom(Color.White),
    val borderColor: ColorType? = null,
    val elevation: ElevationType = ElevationType.None,
    val shape: ShapeType = ShapeType.Medium,
    val spacing: SpacingType = SpacingType.Medium,
    val sizeToken: SizeToken = SizeToken.Medium,
    val fontType: FontType = FontType.Body
)