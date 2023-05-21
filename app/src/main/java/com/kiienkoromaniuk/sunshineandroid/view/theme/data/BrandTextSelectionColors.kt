package com.kiienkoromaniuk.sunshineandroid.view.theme.data

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

object BrandTextSelectionColors {
    private const val BACKGROUND_COLOR_ALPHA = 0.4F

    val primary: TextSelectionColors
        @Composable
        get() = TextSelectionColors(
            handleColor = BrandTheme.colors.N800,
            backgroundColor = BrandTheme.colors.N800.copy(alpha = BACKGROUND_COLOR_ALPHA),
        )
}
