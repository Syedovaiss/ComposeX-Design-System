package com.ovais.composex_core.components.style

sealed interface ComponentState {
    data object Default : ComponentState
    data object Pressed : ComponentState
    data object Disabled : ComponentState
    data object Focused : ComponentState
    data object Hovered : ComponentState
}