package com.ovais.composex_core.core.configuration

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface SizeToken {
    data object Small : SizeToken
    data object Medium : SizeToken
    data object Large : SizeToken
    data object ExtraLarge : SizeToken
    data class Custom(val size: Dp) : SizeToken
}

val SizeToken.toSize: Dp
    get() = when (this) {
        SizeToken.Small -> 16.dp
        SizeToken.Medium -> 24.dp
        SizeToken.Large -> 32.dp
        SizeToken.ExtraLarge -> 48.dp
        is SizeToken.Custom -> this.size
    }