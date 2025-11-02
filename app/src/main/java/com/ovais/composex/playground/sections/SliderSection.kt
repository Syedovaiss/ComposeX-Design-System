package com.ovais.composex.playground.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.ovais.composex_core.components.slider.ComposeXSlider
import com.ovais.composex_core.components.style.ComponentVariant

/**
 * Showcase section for slider component
 */
@Composable
fun SliderSection() {
    // Isolated state for each slider
    var sliderValue1 by remember { mutableStateOf(0.5f) }
    var discreteValue by remember { mutableStateOf(0.5f) }
    var customValue by remember { mutableStateOf(50f) }

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
                    text = "Slider",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Continuous Slider",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Value: ${String.format("%.2f", sliderValue1)}",
                    fontSize = 14.sp
                )
                ComposeXSlider(
                    value = sliderValue1,
                    onValueChange = { sliderValue1 = it },
                    variant = ComponentVariant.Primary,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Discrete Slider (Steps)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Value: ${String.format("%.2f", discreteValue)}",
                    fontSize = 14.sp
                )
                ComposeXSlider(
                    value = discreteValue,
                    onValueChange = { discreteValue = it },
                    steps = 5,
                    variant = ComponentVariant.Success,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Custom Range (0-100)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Value: ${customValue.toInt()}",
                    fontSize = 14.sp
                )
                ComposeXSlider(
                    value = customValue,
                    onValueChange = { customValue = it },
                    valueRange = 0f..100f,
                    variant = ComponentVariant.Info,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Disabled",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                ComposeXSlider(
                    value = 0.5f,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}

