package com.ovais.composex_core.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.button.ComposeXButton
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.SpacingType
import com.ovais.composex_core.core.configuration.toDp
import com.ovais.composex_core.core.configuration.toShape

/**
 * ComposeX Dialog component following the design system
 *
 * @param onDismissRequest Callback when dialog should be dismissed
 * @param modifier Modifier for the dialog
 * @param title Dialog title (optional)
 * @param text Dialog message text
 * @param confirmButtonText Text for confirm button (optional)
 * @param dismissButtonText Text for dismiss button (optional)
 * @param onConfirm Callback for confirm button
 * @param onDismiss Callback for dismiss button
 * @param variant Component variant for buttons
 * @param sizeToken Size token
 * @param properties Dialog properties
 */
@Composable
fun ComposeXDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String? = null,
    text: String,
    confirmButtonText: String? = null,
    dismissButtonText: String = "Cancel",
    onConfirm: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    properties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        val style = componentStyleOf(
            variant = variant,
            state = ComponentState.Default,
            sizeToken = sizeToken
        )

        val backgroundColor = Color.White
        val shape = style.shape.toShape
        val elevation = style.elevation.toDp
        val padding = style.spacing.toDp

        Card(
            modifier = modifier
                .widthIn(min = 280.dp, max = 560.dp)
                .clip(shape),
            shape = shape,
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = elevation
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(SpacingType.Medium.toDp)
            ) {
                title?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
                    )
                }

                Text(
                    text = text,
                    style = TextStyle(),
                    color = Color.Black.copy(alpha = 0.7f)
                )

                if (confirmButtonText != null || onDismiss != null) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        androidx.compose.foundation.layout.Row(
                            horizontalArrangement = Arrangement.spacedBy(SpacingType.Small.toDp)
                        ) {
                            if (onDismiss != null) {
                                TextButton(onClick = {
                                    onDismiss()
                                    onDismissRequest()
                                }) {
                                    Text(text = dismissButtonText)
                                }
                            }
                            if (confirmButtonText != null && onConfirm != null) {
                                ComposeXButton(
                                    text = confirmButtonText,
                                    onClick = {
                                        onConfirm()
                                        onDismissRequest()
                                    },
                                    variant = variant,
                                    sizeToken = sizeToken
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
