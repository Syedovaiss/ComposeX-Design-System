package com.ovais.composex_core.core.configuration

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface ShapeType {
    data object None : ShapeType
    data object ExtraSmall : ShapeType
    data object Small : ShapeType
    data object Medium : ShapeType
    data object Large : ShapeType
    data object ExtraLarge : ShapeType
    data object Circle : ShapeType
    data object Cut : ShapeType
    data class Custom(val radius: Dp) : ShapeType
}

val ShapeType.toShape: Shape
    get() = when (this) {
        ShapeType.None -> RoundedCornerShape(0.dp)
        ShapeType.ExtraSmall -> RoundedCornerShape(4.dp)
        ShapeType.Small -> RoundedCornerShape(8.dp)
        ShapeType.Medium -> RoundedCornerShape(12.dp)
        ShapeType.Large -> RoundedCornerShape(16.dp)
        ShapeType.ExtraLarge -> RoundedCornerShape(24.dp)
        ShapeType.Circle -> CircleShape
        ShapeType.Cut -> CutCornerShape(8.dp)
        is ShapeType.Custom -> RoundedCornerShape(this.radius)
    }