package com.ovais.composex_core.core.configuration

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface ElevationType {
    data object None : ElevationType
    data object Low : ElevationType
    data object Medium : ElevationType
    data object High : ElevationType
    data object ExtraHigh : ElevationType
    data class Custom(val dp: Dp) : ElevationType
}

val ElevationType.toDp: Dp
    get() = when (this) {
        ElevationType.None -> 0.dp
        ElevationType.Low -> 2.dp
        ElevationType.Medium -> 4.dp
        ElevationType.High -> 8.dp
        ElevationType.ExtraHigh -> 16.dp
        is ElevationType.Custom -> this.dp
    }