package com.ovais.composex.playground.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.ovais.composex_core.components.button.ComposeXButton
import com.ovais.composex_core.components.button.ComposeXFilledTonalButton
import com.ovais.composex_core.components.button.ComposeXOutlinedButton
import com.ovais.composex_core.components.button.ComposeXTextButton
import com.ovais.composex_core.components.card.ComposeXCard
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken

/**
 * Showcase section for all button variants
 */
@Composable
fun ButtonShowcaseSection() {
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
                    text = "Buttons",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Filled Buttons
                ButtonVariantGroup(
                    title = "Filled Button",
                    variants = listOf(
                        ComponentVariant.Primary,
                        ComponentVariant.Secondary,
                        ComponentVariant.Success,
                        ComponentVariant.Danger,
                        ComponentVariant.Warning,
                        ComponentVariant.Info
                    )
                ) { variant ->
                    ComposeXButton(
                        text = variant.toString().replace("ComponentVariant.", ""),
                        onClick = {},
                        variant = variant,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Divider()

                // Outlined Buttons
                Text(
                    text = "Outlined Button",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ComposeXOutlinedButton(
                        text = "Primary",
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXOutlinedButton(
                        text = "Success",
                        onClick = {},
                        variant = ComponentVariant.Success,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXOutlinedButton(
                        text = "Danger",
                        onClick = {},
                        variant = ComponentVariant.Danger,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Divider()

                // Text Buttons
                Text(
                    text = "Text Button (Unfilled)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ComposeXTextButton(
                        text = "Primary",
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXTextButton(
                        text = "Success",
                        onClick = {},
                        variant = ComponentVariant.Success,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXTextButton(
                        text = "Danger",
                        onClick = {},
                        variant = ComponentVariant.Danger,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Divider()

                // Filled Tonal Buttons
                Text(
                    text = "Filled Tonal Button",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ComposeXFilledTonalButton(
                        text = "Primary",
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXFilledTonalButton(
                        text = "Success",
                        onClick = {},
                        variant = ComponentVariant.Success,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXFilledTonalButton(
                        text = "Info",
                        onClick = {},
                        variant = ComponentVariant.Info,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Divider()

                // Size Variations
                Text(
                    text = "Size Variations",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ComposeXButton(
                        text = "Small",
                        onClick = {},
                        sizeToken = SizeToken.Small
                    )
                    ComposeXButton(
                        text = "Medium (Default)",
                        onClick = {},
                        sizeToken = SizeToken.Medium
                    )
                    ComposeXButton(
                        text = "Large",
                        onClick = {},
                        sizeToken = SizeToken.Large
                    )
                    ComposeXButton(
                        text = "Extra Large",
                        onClick = {},
                        sizeToken = SizeToken.ExtraLarge
                    )
                }

                Divider()

                // States
                Text(
                    text = "Button States",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ComposeXButton(
                        text = "Enabled",
                        onClick = {},
                        enabled = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXButton(
                        text = "Disabled",
                        onClick = {},
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    )
}

@Composable
private fun ButtonVariantGroup(
    title: String,
    variants: List<ComponentVariant>,
    buttonContent: @Composable (ComponentVariant) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            variants.forEach { variant ->
                buttonContent(variant)
            }
        }
    }
}

