package com.ovais.composex_core.core.configuration

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


sealed interface SpacingType {
    data object None : SpacingType
    data object ExtraSmall : SpacingType
    data object Small : SpacingType
    data object Medium : SpacingType
    data object Large : SpacingType
    data object ExtraLarge : SpacingType
    data class Custom(val size: Dp) : SpacingType
}

val SpacingType.toDp: Dp
    get() = when (this) {
        SpacingType.None -> 0.dp
        SpacingType.ExtraSmall -> 4.dp
        SpacingType.Small -> 8.dp
        SpacingType.Medium -> 16.dp
        SpacingType.Large -> 24.dp
        SpacingType.ExtraLarge -> 32.dp
        is SpacingType.Custom -> this.size
    }