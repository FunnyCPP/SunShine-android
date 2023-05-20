package com.kiienkoromaniuk.sunshineandroid.view.theme.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Immutable
data class BrandTypography(
    val header: TextStyle,
    val normal: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val copy: TextStyle,
    val boldCopy: TextStyle,
    val smallCopy: TextStyle,
    val button: TextStyle,
)
