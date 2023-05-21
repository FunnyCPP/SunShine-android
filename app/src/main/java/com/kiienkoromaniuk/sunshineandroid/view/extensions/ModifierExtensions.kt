package com.kiienkoromaniuk.sunshineandroid.view.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

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
