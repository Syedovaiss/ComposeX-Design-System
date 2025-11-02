package com.ovais.composex_core.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toDp
import com.ovais.composex_core.core.configuration.toShape

/**
 * Simple Alert Dialog using Material3
 *
 * @param onDismissRequest Callback when dialog should be dismissed
 * @param confirmButton Confirm button composable
 * @param modifier Modifier for the dialog
 * @param dismissButton Optional dismiss button composable
 * @param icon Optional icon composable
 * @param title Optional title composable
 * @param text Optional text composable
 * @param variant Component variant for styling
 * @param sizeToken Size token
 */
@Composable
fun ComposeXAlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium
) {
    val style = componentStyleOf(
        variant = variant,
        state = ComponentState.Default,
        sizeToken = sizeToken
    )

    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        icon = icon,
        title = title,
        text = text,
        modifier = modifier,
        shape = style.shape.toShape,
        containerColor = Color.White,
        tonalElevation = style.elevation.toDp
    )
}

