package com.ovais.composex_core.components.style

import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.ElevationType
import com.ovais.composex_core.core.configuration.ShapeType

sealed interface ComponentVariant {
    data object Primary : ComponentVariant
    data object Secondary : ComponentVariant
    data object Tertiary : ComponentVariant
    data object Success : ComponentVariant
    data object Danger : ComponentVariant
    data object Warning : ComponentVariant
    data object Info : ComponentVariant
    data class Custom(
        val backgroundColor: ColorType.Custom,
        val contentColor: ColorType.Custom,
        val borderColor: ColorType.Custom? = null,
        val elevation: ElevationType = ElevationType.None,
        val shape: ShapeType = ShapeType.Medium,
    ) : ComponentVariant
}
