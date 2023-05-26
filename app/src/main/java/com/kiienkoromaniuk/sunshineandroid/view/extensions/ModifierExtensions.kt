package com.kiienkoromaniuk.sunshineandroid.view.extensions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

private const val DEFAULT_PADDING = 0F

fun Modifier.skeleton(
    radius: Dp = 0.dp,
): Modifier = composed {
    this
        .alpha(0.5F)
        .background(
            color = BrandTheme.colors.N300,
            shape = RoundedCornerShape(radius),
        )
}

fun Modifier.topShadow(
    shadowSize: Dp = 30.dp,
): Modifier = composed {
    val paddingPx = with(LocalDensity.current) { shadowSize.toPx() }
    this.drawWithContent {
        clipRect(
            left = DEFAULT_PADDING,
            top = -paddingPx,
            right = size.width,
            bottom = size.height,
        ) {
            this@drawWithContent.drawContent()
        }
    }
}

@ExperimentalFoundationApi
fun Modifier.clickable(
    withIndication: Boolean = true,
    onClick: () -> Unit = {},
    onLongClick: () -> Unit = {},
): Modifier = composed {
    if (withIndication) {
        this.combinedClickable(
            onClick = onClick,
            onLongClick = onLongClick,
        )
    } else {
        this.combinedClickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = onClick,
            onLongClick = onLongClick,
        )
    }
}

fun Modifier.bottomShadow(
    shadowSize: Dp = 20.dp,
): Modifier = composed {
    val paddingPx = with(LocalDensity.current) { shadowSize.toPx() }
    this.drawWithContent {
        clipRect(
            left = DEFAULT_PADDING,
            top = DEFAULT_PADDING,
            right = size.width,
            bottom = size.height + paddingPx,
        ) {
            this@drawWithContent.drawContent()
        }
    }
}
