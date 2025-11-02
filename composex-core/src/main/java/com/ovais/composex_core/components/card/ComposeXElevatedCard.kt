package com.ovais.composex_core.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toDp
import com.ovais.composex_core.core.configuration.toShape

/**
 * Elevated card variant with stronger elevation
 *
 * @param content Card content
 * @param modifier Modifier for the card
 * @param onClick Optional click callback (makes card clickable)
 * @param variant Component variant for styling
 * @param sizeToken Size token for padding
 * @param enabled Whether the card is enabled (when clickable)
 * @param containerColor Override container color (optional)
 */
@Composable
fun ComposeXElevatedCard(
    content: @Composable (PaddingValues) -> Unit,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    enabled: Boolean = true,
    containerColor: Color? = null
) {
    val state = if (enabled) ComponentState.Default else ComponentState.Disabled
    val style = componentStyleOf(variant = variant, state = state, sizeToken = sizeToken)

    val backgroundColor = containerColor ?: Color.White
    val shape = style.shape.toShape
    val padding = style.spacing.toDp

    val cardModifier = modifier
        .fillMaxWidth()
        .then(
            if (onClick != null) {
                Modifier.clickable(
                    enabled = enabled,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                )
            } else Modifier
        )

    ElevatedCard(
        modifier = cardModifier,
        shape = shape,
        colors = CardDefaults.elevatedCardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = style.elevation.toDp * 1.5f
        )
    ) {
        Box(
            modifier = Modifier.padding(padding)
        ) {
            content(PaddingValues(padding))
        }
    }
}

