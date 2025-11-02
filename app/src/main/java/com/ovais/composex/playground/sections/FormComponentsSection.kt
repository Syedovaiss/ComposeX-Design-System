package com.ovais.composex.playground.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
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
import com.ovais.composex_core.components.checkbox.ComposeXCheckbox
import com.ovais.composex_core.components.input.ComposeXTextField
import com.ovais.composex_core.components.radio.ComposeXRadioButton
import com.ovais.composex_core.components.switch.ComposeXSwitch
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken

/**
 * Showcase section for form components
 */
@Composable
fun FormComponentsSection() {
    // Isolated state for each component to prevent cross-interaction
    var textFieldValue1 by remember { mutableStateOf("") }
    var textFieldValue2 by remember { mutableStateOf("") }
    var textFieldValue3 by remember { mutableStateOf("") }
    var checkboxChecked1 by remember { mutableStateOf(false) }
    var checkboxChecked2 by remember { mutableStateOf(false) }
    var checkboxChecked3 by remember { mutableStateOf(false) }
    var radioSelected1 by remember { mutableStateOf(false) }
    var radioSelected2 by remember { mutableStateOf(false) }
    var radioSelected3 by remember { mutableStateOf(false) }
    var switchChecked1 by remember { mutableStateOf(false) }
    var switchChecked2 by remember { mutableStateOf(false) }
    var switchChecked3 by remember { mutableStateOf(false) }

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
                    text = "Form Components",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Text Field
                Text(
                    text = "Text Field",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                ComposeXTextField(
                    value = textFieldValue1,
                    onValueChange = { textFieldValue1 = it },
                    placeholder = "Enter text here...",
                    modifier = Modifier.fillMaxWidth()
                )
                ComposeXTextField(
                    value = textFieldValue2,
                    onValueChange = { textFieldValue2 = it },
                    placeholder = "With error",
                    error = true,
                    supportingText = "Error message here",
                    modifier = Modifier.fillMaxWidth()
                )
                ComposeXTextField(
                    value = textFieldValue3,
                    onValueChange = { textFieldValue3 = it },
                    placeholder = "Disabled",
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Divider()

                // Checkbox
                Text(
                    text = "Checkbox",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                ComposeXCheckbox(
                    checked = checkboxChecked1,
                    onCheckedChange = { checkboxChecked1 = it },
                    label = "Checkbox with label",
                    variant = ComponentVariant.Primary
                )
                ComposeXCheckbox(
                    checked = checkboxChecked2,
                    onCheckedChange = { checkboxChecked2 = it },
                    label = "Success variant",
                    variant = ComponentVariant.Success
                )
                ComposeXCheckbox(
                    checked = checkboxChecked3,
                    onCheckedChange = { checkboxChecked3 = it },
                    label = "Disabled",
                    enabled = false
                )

                Divider()

                // Radio Button
                Text(
                    text = "Radio Button",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                ComposeXRadioButton(
                    selected = radioSelected1,
                    onClick = { radioSelected1 = !radioSelected1 },
                    label = "Radio with label",
                    variant = ComponentVariant.Primary
                )
                ComposeXRadioButton(
                    selected = radioSelected2,
                    onClick = { radioSelected2 = !radioSelected2 },
                    label = "Danger variant",
                    variant = ComponentVariant.Danger
                )
                ComposeXRadioButton(
                    selected = radioSelected3,
                    onClick = { radioSelected3 = !radioSelected3 },
                    label = "Disabled",
                    enabled = false
                )

                Divider()

                // Switch
                Text(
                    text = "Switch",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                ComposeXSwitch(
                    checked = switchChecked1,
                    onCheckedChange = { switchChecked1 = it },
                    label = "Switch with label",
                    variant = ComponentVariant.Primary
                )
                ComposeXSwitch(
                    checked = switchChecked2,
                    onCheckedChange = { switchChecked2 = it },
                    label = "Success variant",
                    variant = ComponentVariant.Success
                )
                ComposeXSwitch(
                    checked = switchChecked3,
                    onCheckedChange = { switchChecked3 = it },
                    label = "Disabled",
                    enabled = false
                )
            }
        }
    )
}

