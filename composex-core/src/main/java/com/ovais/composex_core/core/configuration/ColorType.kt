package com.ovais.composex_core.core.configuration

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.ovais.composex_core.core.theme.DangerDark
import com.ovais.composex_core.core.theme.DangerLight
import com.ovais.composex_core.core.theme.DisabledDark
import com.ovais.composex_core.core.theme.DisabledLight
import com.ovais.composex_core.core.theme.InfoDark
import com.ovais.composex_core.core.theme.InfoLight
import com.ovais.composex_core.core.theme.SuccessDark
import com.ovais.composex_core.core.theme.SuccessLight
import com.ovais.composex_core.core.theme.ThemeProvider
import com.ovais.composex_core.core.theme.WarningDark
import com.ovais.composex_core.core.theme.WarningLight

sealed interface ColorType : ThemeProvider {
    data class Success(
        override val isLight: Boolean? = null
    ) : ColorType

    data class Danger(
        override val isLight: Boolean? = null
    ) : ColorType

    data class Warning(
        override val isLight: Boolean? = null
    ) : ColorType

    data class Info(
        override val isLight: Boolean? = null
    ) : ColorType

    data class Disabled(
        override val isLight: Boolean? = null
    ) : ColorType

    data class Custom(
        val color: Color,
        override val isLight: Boolean? = null
    ) : ColorType
}

@Composable
fun ColorType.toColor(): Color {
    // Default to dark theme when isLight is null
    val isLightTheme: Boolean = this.isLight ?: !isSystemInDarkTheme()
    
    return when (this) {
        is ColorType.Info -> if (isLightTheme) InfoLight else InfoDark
        is ColorType.Success -> if (isLightTheme) SuccessLight else SuccessDark
        is ColorType.Danger -> if (isLightTheme) DangerLight else DangerDark
        is ColorType.Warning -> if (isLightTheme) WarningLight else WarningDark
        is ColorType.Disabled -> if (isLightTheme) DisabledLight else DisabledDark
        is ColorType.Custom -> this.color
    }
}
