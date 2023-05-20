package com.kiienko_romaniuk.sunshine_android.view.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import com.kiienko_romaniuk.sunshine_android.view.theme.data.BrandShapes

val LocalBrandShapes = staticCompositionLocalOf {
    BrandShapes(
        bottomSheetShape = RoundedCornerShape(ZeroCornerSize),
        outlinedTextFieldShape = RoundedCornerShape(ZeroCornerSize),
    )
}

val BrandShapes = BrandShapes(
    bottomSheetShape = RoundedCornerShape(
        topStart = 25.dp,
        topEnd = 25.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp,
    ),
    outlinedTextFieldShape = RoundedCornerShape(
        size = 4.dp,
    ),
)
