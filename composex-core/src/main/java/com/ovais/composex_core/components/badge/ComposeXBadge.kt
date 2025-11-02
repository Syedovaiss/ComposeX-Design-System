package com.ovais.composex_core.components.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor

/**
 * ComposeX Badge component following the design system
 *
 * @param count Badge count to display
 * @param modifier Modifier for the badge
 * @param variant Component variant for styling
 * @param sizeToken Size token
 * @param maxCount Maximum count to display (default: 99, shows 99+ for overflow)
 * @param showZero Whether to show badge when count is 0
 */
@Composable
fun ComposeXBadge(
    count: Int,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Danger,
    sizeToken: SizeToken = SizeToken.Small,
    maxCount: Int = 99,
    showZero: Boolean = false
) {
    if (!showZero && count <= 0) return

    val style = componentStyleOf(
        variant = variant,
        state = ComponentState.Default,
        sizeToken = sizeToken
    )

    val backgroundColor = style.backgroundColor.toColor()
    val contentColor = style.contentColor.toColor()

    val badgeSize = when (sizeToken) {
        SizeToken.Small -> 18.dp
        SizeToken.Medium -> 22.dp
        SizeToken.Large -> 26.dp
        SizeToken.ExtraLarge -> 30.dp
        is SizeToken.Custom -> sizeToken.size * 1.1f
    }

    val fontSize = when (sizeToken) {
        SizeToken.Small -> 10.sp
        SizeToken.Medium -> 12.sp
        SizeToken.Large -> 14.sp
        SizeToken.ExtraLarge -> 16.sp
        is SizeToken.Custom -> (sizeToken.size.value * 0.4f).sp
    }

    val displayCount = if (count > maxCount) "$maxCount+" else count.toString()

    Box(
        modifier = modifier
            .size(badgeSize)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = displayCount,
            style = TextStyle(
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            color = contentColor,
            modifier = Modifier
        )
    }
}
