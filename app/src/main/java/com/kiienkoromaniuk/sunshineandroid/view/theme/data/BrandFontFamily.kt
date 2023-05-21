package com.kiienkoromaniuk.sunshineandroid.view.theme.data

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kiienkoromaniuk.sunshineandroid.R

object BrandFontFamily {
    val Roboto = FontFamily(
        Font(
            resId = R.font.roboto_bold,
            weight = FontWeight.W700,
        ),
        Font(
            resId = R.font.roboto_regular,
            weight = FontWeight.W400,
        ),
    )
}
