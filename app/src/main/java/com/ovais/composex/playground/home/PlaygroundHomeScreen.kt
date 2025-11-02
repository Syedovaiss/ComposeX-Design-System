package com.ovais.composex.playground.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovais.composex_core.components.card.ComposeXCard

data class ComponentCategory(
    val title: String,
    val description: String,
    val onClick: () -> Unit
)

@Composable
fun PlaygroundHomeScreen(
    onNavigateToButtons: () -> Unit,
    onNavigateToForm: () -> Unit,
    onNavigateToSelection: () -> Unit,
    onNavigateToFeedback: () -> Unit,
    onNavigateToContainer: () -> Unit,
    onNavigateToSlider: () -> Unit
) {
    val categories = listOf(
        ComponentCategory(
            title = "Buttons",
            description = "Filled, Outlined, Text, and Tonal button variants",
            onClick = onNavigateToButtons
        ),
        ComponentCategory(
            title = "Form Components",
            description = "Text Fields, Checkboxes, Radio Buttons, and Switches",
            onClick = onNavigateToForm
        ),
        ComponentCategory(
            title = "Selection Components",
            description = "Dropdowns, Chips, and Counters",
            onClick = onNavigateToSelection
        ),
        ComponentCategory(
            title = "Feedback Components",
            description = "Progress Indicators, Badges, Dialogs, and Snackbars",
            onClick = onNavigateToFeedback
        ),
        ComponentCategory(
            title = "Container Components",
            description = "Cards and Bottom Sheets",
            onClick = onNavigateToContainer
        ),
        ComponentCategory(
            title = "Slider",
            description = "Slider component with various configurations",
            onClick = onNavigateToSlider
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "ComposeX Design System",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Interactive playground showcasing all components",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        categories.forEach { category ->
            ComposeXCard(
                modifier = Modifier.fillMaxWidth(),
                onClick = category.onClick,
                content = { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(paddingValues),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = category.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = category.description,
                            fontSize = 14.sp
                        )
                    }
                }
            )
        }
    }
}

