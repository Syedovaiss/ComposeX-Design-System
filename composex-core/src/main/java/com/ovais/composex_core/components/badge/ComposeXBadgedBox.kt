package com.ovais.composex_core.components.badge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken

/**
 * Badge that can be overlayed on another composable
 *
 * @param badgeCount Badge count to display
 * @param modifier Modifier for the box
 * @param variant Component variant for styling
 * @param sizeToken Size token
 * @param maxCount Maximum count to display (default: 99, shows 99+ for overflow)
 * @param showZero Whether to show badge when count is 0
 * @param content Content composable to badge
 */
@Composable
fun ComposeXBadgedBox(
    badgeCount: Int,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Danger,
    sizeToken: SizeToken = SizeToken.Small,
    maxCount: Int = 99,
    showZero: Boolean = false,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        content()

        if (showZero || badgeCount > 0) {
            ComposeXBadge(
                count = badgeCount,
                variant = variant,
                sizeToken = sizeToken,
                maxCount = maxCount,
                showZero = showZero,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 8.dp, y = (-8).dp)
            )
        }
    }
}

