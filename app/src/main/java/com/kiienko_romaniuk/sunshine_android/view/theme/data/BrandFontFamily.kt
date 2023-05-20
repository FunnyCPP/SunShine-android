package com.kiienko_romaniuk.sunshine_android.view.theme.data

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kiienko_romaniuk.sunshine_android.R

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
