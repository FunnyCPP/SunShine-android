package com.kiienkoromaniuk.sunshineandroid.view.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.kiienkoromaniuk.sunshineandroid.view.theme.data.BrandColors
import com.kiienkoromaniuk.sunshineandroid.view.theme.data.BrandDimensions
import com.kiienkoromaniuk.sunshineandroid.view.theme.data.BrandShapes
import com.kiienkoromaniuk.sunshineandroid.view.theme.data.BrandTypography

@Composable
fun BrandTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalBrandColors provides BrandColors,
        LocalBrandTypography provides BrandTypography,
        LocalBrandDimensions provides BrandDimensions,
        LocalBrandShapes provides BrandShapes,
    ) {
        MaterialTheme(content = content)
    }
}

object BrandTheme {
    val colors: BrandColors
        @Composable
        get() = LocalBrandColors.current

    val typography: BrandTypography
        @Composable
        get() = LocalBrandTypography.current

    val dimensions: BrandDimensions
        @Composable
        get() = LocalBrandDimensions.current

    val shapes: BrandShapes
        @Composable
        get() = LocalBrandShapes.current
}
