package com.kiienko_romaniuk.sunshine_android.view.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kiienko_romaniuk.sunshine_android.view.theme.data.BrandDimensions

val LocalBrandDimensions = staticCompositionLocalOf {
    BrandDimensions(
        none = Dp.Unspecified,
        extraSmall = Dp.Unspecified,
        verySmall = Dp.Unspecified,
        small = Dp.Unspecified,
        normal = Dp.Unspecified,
        large = Dp.Unspecified,
        veryLarge = Dp.Unspecified,
        extraLarge = Dp.Unspecified,
    )
}

val BrandDimensions = BrandDimensions(
    none = 0.dp,
    extraSmall = 2.dp,
    verySmall = 4.dp,
    small = 8.dp,
    normal = 10.dp,
    large = 12.dp,
    veryLarge = 16.dp,
    extraLarge = 20.dp,
)
