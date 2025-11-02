package com.ovais.composex_core.components.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken

/**
 * Snackbar host for displaying snackbars
 *
 * @param hostState Snackbar host state for managing snackbars
 * @param modifier Modifier for the snackbar host
 * @param variant Component variant for styling
 * @param sizeToken Size token
 */
@Composable
fun ComposeXSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
        snackbar = { snackbarData ->
            val hasAction = snackbarData.visuals.actionLabel != null
            ComposeXSnackbar(
                message = snackbarData.visuals.message,
                actionLabel = snackbarData.visuals.actionLabel,
                onAction = if (hasAction) {
                    { snackbarData.performAction() }
                } else {
                    null
                },
                variant = variant,
                sizeToken = sizeToken
            )
        }
    )
}

