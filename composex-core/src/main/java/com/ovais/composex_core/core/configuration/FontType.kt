package com.ovais.composex_core.core.configuration

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed interface FontType {
    data object Display : FontType
    data object Title : FontType
    data object Subtitle : FontType
    data object Body : FontType
    data object Caption : FontType
    data class Custom(val size: Dp) : FontType
}

val FontType.toSize: Dp
    get() = when (this) {
        FontType.Display -> 32.dp
        FontType.Title -> 24.dp
        FontType.Subtitle -> 18.dp
        FontType.Body -> 14.dp
        FontType.Caption -> 12.dp
        is FontType.Custom -> this.size
    }