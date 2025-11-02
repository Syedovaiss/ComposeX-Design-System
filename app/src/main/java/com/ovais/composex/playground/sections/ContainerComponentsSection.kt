package com.ovais.composex.playground.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovais.composex_core.components.card.ComposeXCard
import com.ovais.composex_core.components.card.ComposeXElevatedCard
import com.ovais.composex_core.components.bottomsheet.ComposeXModalBottomSheet

/**
 * Showcase section for container components
 */
@Composable
fun ContainerComponentsSection() {
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Container Components",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Card
        ComposeXCard(
            modifier = Modifier.fillMaxWidth(),
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Standard Card",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "This is a standard card component with default elevation.",
                        fontSize = 14.sp
                    )
                }
            }
        )

        // Elevated Card
        ComposeXElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Elevated Card",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "This is an elevated card with higher elevation.",
                        fontSize = 14.sp
                    )
                }
            }
        )

        // Clickable Card
        ComposeXCard(
            modifier = Modifier.fillMaxWidth(),
            onClick = { showBottomSheet = true },
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Clickable Card",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Tap to open bottom sheet",
                        fontSize = 14.sp
                    )
                }
            }
        )

        // Bottom Sheet
        if (showBottomSheet) {
            @OptIn(ExperimentalMaterial3Api::class)
            ComposeXModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                title = "Bottom Sheet",
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "This is a modal bottom sheet component.",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "You can add any content here.",
                            fontSize = 16.sp
                        )
                        com.ovais.composex_core.components.button.ComposeXButton(
                            text = "Close",
                            onClick = { showBottomSheet = false },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            )
        }
    }
}

