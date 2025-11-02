package com.ovais.composex_core.components.progress

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
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
 * ComposeX Linear Progress Indicator component following the design system
 *
 * @param progress Progress value (0.0f to 1.0f), null for indeterminate
 * @param modifier Modifier for the progress indicator
 * @param variant Component variant for styling
 * @param sizeToken Size token for height
 */
@Composable
fun ComposeXLinearProgressIndicator(
    progress: Float? = null,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Small
) {
    val style = componentStyleOf(
        variant = variant,
        state = ComponentState.Default,
        sizeToken = sizeToken
    )

    val indicatorColor = style.backgroundColor.toColor()
    val trackColor = indicatorColor.copy(alpha = 0.2f)

    val height = when (sizeToken) {
        SizeToken.Small -> 4.dp
        SizeToken.Medium -> 6.dp
        SizeToken.Large -> 8.dp
        SizeToken.ExtraLarge -> 10.dp
        is SizeToken.Custom -> sizeToken.size * 0.15f
    }

    LinearProgressIndicator(
        progress = { progress?.coerceIn(0f, 1f).orZero },
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        color = indicatorColor,
        trackColor = trackColor,
        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
    )
}

