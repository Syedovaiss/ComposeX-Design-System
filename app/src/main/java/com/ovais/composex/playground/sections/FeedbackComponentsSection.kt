package com.ovais.composex.playground.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovais.composex_core.components.badge.ComposeXBadge
import com.ovais.composex_core.components.badge.ComposeXBadgedBox
import com.ovais.composex_core.components.card.ComposeXCard
import com.ovais.composex_core.components.dialog.ComposeXDialog
import com.ovais.composex_core.components.progress.ComposeXCircularProgressIndicator
import com.ovais.composex_core.components.progress.ComposeXLinearProgressIndicator
import com.ovais.composex_core.components.snackbar.ComposeXSnackbarHost
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.launch

/**
 * Showcase section for feedback components
 */
@Composable
fun FeedbackComponentsSection() {
    // Isolated state
    var progressValue1 by remember { mutableStateOf(0.3f) }
    var progressValue2 by remember { mutableStateOf(0.3f) }
    var showDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    ComposeXCard(
        modifier = Modifier.fillMaxWidth(),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Feedback Components",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Progress Indicators
                Text(
                    text = "Progress Indicators",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                ComposeXLinearProgressIndicator(
                    progress = progressValue1,
                    variant = ComponentVariant.Primary
                )
                ComposeXLinearProgressIndicator(
                    progress = null,
                    variant = ComponentVariant.Success
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ComposeXCircularProgressIndicator(
                        progress = progressValue2,
                        variant = ComponentVariant.Primary
                    )
                    ComposeXCircularProgressIndicator(
                        progress = null,
                        variant = ComponentVariant.Success
                    )
                }

                Divider()

                // Badge
                Text(
                    text = "Badge",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ComposeXBadge(
                        count = 5,
                        variant = ComponentVariant.Danger
                    )
                    ComposeXBadge(
                        count = 99,
                        maxCount = 99,
                        variant = ComponentVariant.Success
                    )
                    ComposeXBadge(
                        count = 150,
                        maxCount = 99,
                        variant = ComponentVariant.Warning
                    )
                }
                
                // Badged Box
                ComposeXBadgedBox(
                    badgeCount = 3,
                    variant = ComponentVariant.Danger
                ) {
                    ComposeXCard(
                        modifier = Modifier.fillMaxWidth(),
                        content = { padding ->
                            Text(
                                text = "Card with badge",
                                modifier = Modifier.padding(padding)
                            )
                        }
                    )
                }

                Divider()

                // Dialog
                Text(
                    text = "Dialog",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                com.ovais.composex_core.components.button.ComposeXButton(
                    text = "Show Dialog",
                    onClick = { showDialog = true }
                )

                if (showDialog) {
                    ComposeXDialog(
                        onDismissRequest = { showDialog = false },
                        title = "Sample Dialog",
                        text = "This is a sample dialog demonstrating the ComposeX dialog component.",
                        confirmButtonText = "OK",
                        dismissButtonText = "Cancel",
                        onConfirm = { showDialog = false },
                        onDismiss = { showDialog = false }
                    )
                }

                Divider()

                // Snackbar
                Text(
                    text = "Snackbar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                com.ovais.composex_core.components.button.ComposeXButton(
                    text = "Show Snackbar",
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "This is a snackbar message!",
                                actionLabel = "Action"
                            )
                        }
                    }
                )
                
                ComposeXSnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

