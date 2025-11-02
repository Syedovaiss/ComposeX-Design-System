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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ovais.composex_core.components.chip.ComposeXChip
import com.ovais.composex_core.components.counter.ComposeXCounter
import com.ovais.composex_core.components.dropdown.ComposeXDropdown
import com.ovais.composex_core.components.dropdown.DropdownItem
import com.ovais.composex_core.components.card.ComposeXCard
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken

/**
 * Showcase section for selection components
 */
@Composable
fun SelectionComponentsSection() {
    // Isolated state for each component
    var counterValue1 by remember { mutableStateOf(0) }
    var counterValue2 by remember { mutableStateOf(0) }
    var counterValue3 by remember { mutableStateOf(0) }
    var selectedDropdownItem by remember {
        mutableStateOf<DropdownItem?>(null)
    }

    val dropdownItems = remember {
        listOf(
            DropdownItem(id = "1", text = "Option 1"),
            DropdownItem(id = "2", text = "Option 2"),
            DropdownItem(id = "3", text = "Option 3", enabled = false),
            DropdownItem(id = "4", text = "Option 4")
        )
    }

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
                    text = "Selection Components",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                // Dropdown
                Text(
                    text = "Dropdown",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                ComposeXDropdown(
                    selectedItem = selectedDropdownItem,
                    items = dropdownItems,
                    onItemSelected = { selectedDropdownItem = it },
                    modifier = Modifier.fillMaxWidth()
                )

                Divider()

                // Chip
                Text(
                    text = "Chip",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                
                var chip1Selected by remember { mutableStateOf(false) }
                var chip2Selected by remember { mutableStateOf(false) }
                var chip3Selected by remember { mutableStateOf(false) }
                
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ComposeXChip(
                            text = "Primary",
                            selected = chip1Selected,
                            onSelectionChange = { chip1Selected = it },
                            variant = ComponentVariant.Primary,
                            modifier = Modifier.weight(1f)
                        )
                        ComposeXChip(
                            text = "Success",
                            selected = chip2Selected,
                            onSelectionChange = { chip2Selected = it },
                            variant = ComponentVariant.Success,
                            modifier = Modifier.weight(1f)
                        )
                        ComposeXChip(
                            text = "Info",
                            selected = chip3Selected,
                            onSelectionChange = { chip3Selected = it },
                            variant = ComponentVariant.Info,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    ComposeXChip(
                        text = "Disabled",
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Divider()

                // Counter
                Text(
                    text = "Counter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ComposeXCounter(
                        value = counterValue1,
                        onValueChange = { counterValue1 = it },
                        minValue = 0,
                        maxValue = 10,
                        variant = ComponentVariant.Primary,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXCounter(
                        value = counterValue2,
                        onValueChange = { counterValue2 = it },
                        minValue = 0,
                        maxValue = 10,
                        variant = ComponentVariant.Success,
                        sizeToken = SizeToken.Small,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ComposeXCounter(
                        value = counterValue3,
                        onValueChange = { counterValue3 = it },
                        enabled = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    )
}

