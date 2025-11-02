package com.ovais.composex_core.components.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.SpacingType
import com.ovais.composex_core.core.configuration.toDp

/**
 * ComposeX Modal Bottom Sheet component following the design system
 *
 * @param onDismissRequest Callback when bottom sheet should be dismissed
 * @param modifier Modifier for the bottom sheet
 * @param sheetState Sheet state for controlling bottom sheet
 * @param title Optional title text
 * @param content Bottom sheet content
 * @param variant Component variant for styling
 * @param sizeToken Size token for padding
 * @param containerColor Override container color (optional)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeXModalBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { it != SheetValue.Hidden }
    ),
    title: String? = null,
    content: @Composable (PaddingValues) -> Unit,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    containerColor: Color? = null
) {
    val style = componentStyleOf(
        variant = variant,
        state = ComponentState.Default,
        sizeToken = sizeToken
    )

    val backgroundColor = containerColor ?: Color.White
    val padding = style.spacing.toDp
    val shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = modifier,
        shape = shape,
        containerColor = backgroundColor,
        dragHandle = null
    ) {
        Column(
            modifier = Modifier.padding(padding)
        ) {
            title?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = SpacingType.Medium.toDp)
                )
            }
            content(PaddingValues(vertical = SpacingType.Medium.toDp))
        }
    }
}

