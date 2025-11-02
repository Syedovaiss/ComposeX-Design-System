package com.ovais.composex.playground.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovais.composex_core.components.card.ComposeXCard
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.button.ComposeXButton
import com.ovais.composex_core.components.button.ComposeXOutlinedButton
import com.ovais.composex_core.components.button.ComposeXTextButton
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.ColorType
import com.ovais.composex_core.core.configuration.toColor
import androidx.compose.ui.graphics.Color

/**
 * Example section showing how to build custom components using the design system
 */
@Composable
fun CustomComponentExample() {
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
                    text = "Custom Component Example",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Building Custom Components",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "You can use componentStyleOf() to create custom styled components that follow the design system.",
                    fontSize = 14.sp
                )

                // Example: Custom Action Bar
                Text(
                    text = "Example: Custom Action Bar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 8.dp)
                )

                CustomActionBar(
                    title = "Custom Component",
                    onSaveClick = {},
                    onCancelClick = {}
                )

                Divider()

                // Example: Custom Profile Card
                Text(
                    text = "Example: Custom Profile Card",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                CustomProfileCard(
                    name = "John Doe",
                    email = "john.doe@example.com",
                    onEditClick = {},
                    onDeleteClick = {}
                )

                Divider()

                // Usage Instructions
                Text(
                    text = "Usage:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                
                Text(
                    text = "Use componentStyleOf() to get design system styles. All components above demonstrate how to use the design system tokens (variants, sizes, states) to create consistent UI.",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    )
}

/**
 * Example custom component: Action Bar
 */
@Composable
private fun CustomActionBar(
    title: String,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    val style = componentStyleOf(
        variant = ComponentVariant.Primary,
        state = ComponentState.Default,
        sizeToken = SizeToken.Medium
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = style.contentColor.toColor()
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ComposeXTextButton(
                text = "Cancel",
                onClick = onCancelClick
            )
            ComposeXButton(
                text = "Save",
                onClick = onSaveClick
            )
        }
    }
}

/**
 * Example custom component: Profile Card
 */
@Composable
private fun CustomProfileCard(
    name: String,
    email: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    ComposeXCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onEditClick,
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = email,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    ComposeXTextButton(
                        text = "Delete",
                        onClick = onDeleteClick,
                        variant = ComponentVariant.Danger
                    )
                }
            }
        }
    )
}

