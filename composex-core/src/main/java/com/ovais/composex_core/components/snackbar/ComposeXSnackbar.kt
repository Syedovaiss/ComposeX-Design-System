package com.ovais.composex_core.components.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ovais.composex_core.components.builder.componentStyleOf
import com.ovais.composex_core.components.style.ComponentState
import com.ovais.composex_core.components.style.ComponentVariant
import com.ovais.composex_core.core.configuration.SizeToken
import com.ovais.composex_core.core.configuration.toColor
import com.ovais.composex_core.core.configuration.toDp

/**
 * ComposeX Snackbar component following the design system
 *
 * @param message Snackbar message text
 * @param modifier Modifier for the snackbar
 * @param actionLabel Optional action button label
 * @param onAction Optional action callback
 * @param variant Component variant for styling
 * @param sizeToken Size token
 * @param icon Optional icon drawable resource
 */
@Composable
fun ComposeXSnackbar(
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    variant: ComponentVariant = ComponentVariant.Primary,
    sizeToken: SizeToken = SizeToken.Medium,
    @DrawableRes icon: Int? = null
) {
    val style = componentStyleOf(
        variant = variant,
        state = ComponentState.Default,
        sizeToken = sizeToken
    )

    val backgroundColor = style.backgroundColor.toColor()
    val contentColor = style.contentColor.toColor()
    val shape = RoundedCornerShape(4.dp)
    val padding = style.spacing.toDp * 0.5f

    Snackbar(
        modifier = modifier.clip(shape),
        containerColor = backgroundColor,
        contentColor = contentColor,
        shape = shape,
        action = {
            actionLabel?.let { label ->
                onAction?.let { action ->
                    TextButton(onClick = action) {
                        Text(text = label)
                    }
                }
            }
        }
    ) {
        Row(
            modifier = Modifier.padding(padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            icon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = contentColor
                )
            }
            Text(
                text = message,
                style = TextStyle(),
                color = contentColor
            )
        }
    }
}
