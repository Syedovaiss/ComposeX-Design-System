package com.ovais.composex_core.components.progress

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor
import com.ovais.composex_core.utils.orZero

/**
 * ComposeX Circular Progress Indicator component following the design system
 *
 * @param progress Progress value (0.0f to 1.0f), null for indeterminate
 * @param modifier Modifier for the progress indicator
 * @param variant Component variant for styling
 * @param sizeToken Size token for size
 */
@Composable
fun ComposeXCircularProgressIndicator(
    progress: Float? = null,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium
) {
    val style = componentStyleOf(
        variant = variant,
        state = ComponentState.Default,
        sizeToken = sizeToken
    )

    val indicatorColor = style.backgroundColor.toColor()
    val trackColor = indicatorColor.copy(alpha = 0.2f)

    val size = when (sizeToken) {
        SizeToken.Small -> 24.dp
        SizeToken.Medium -> 32.dp
        SizeToken.Large -> 40.dp
        SizeToken.ExtraLarge -> 48.dp
        is SizeToken.Custom -> sizeToken.size
    }

    CircularProgressIndicator(
        progress = { progress?.coerceIn(0f, 1f).orZero },
        modifier = modifier.size(size),
        color = indicatorColor,
        strokeWidth = when (sizeToken) {
            SizeToken.Small -> 2.dp
            SizeToken.Medium -> 3.dp
            SizeToken.Large -> 4.dp
            SizeToken.ExtraLarge -> 5.dp
            is SizeToken.Custom -> sizeToken.size * 0.1f
        },
        trackColor = trackColor,
        strokeCap = ProgressIndicatorDefaults.CircularDeterminateStrokeCap,
    )
}

