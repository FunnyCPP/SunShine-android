package com.kiienko_romaniuk.sunshine_android.view.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kiienko_romaniuk.sunshine_android.view.theme.data.BrandFontFamily
import com.kiienko_romaniuk.sunshine_android.view.theme.data.BrandTypography

val LocalBrandTypography = staticCompositionLocalOf {
    BrandTypography(
        header = TextStyle.Default,
        normal = TextStyle.Default,
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        h3 = TextStyle.Default,
        copy = TextStyle.Default,
        boldCopy = TextStyle.Default,
        smallCopy = TextStyle.Default,
        button = TextStyle.Default,
    )
}

val BrandTypography = BrandTypography(
    header = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        letterSpacing = 0.01.sp,
    ),
    normal = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        letterSpacing = 0.01.sp,
    ),
    h1 = TextStyle(
        fontFamily = BrandFontFamily.Roboto,
        fontWeight = FontWeight.W700,
        fontSize = 22.sp,
        letterSpacing = 0.01.sp,
    ),
    h2 = TextStyle(
        fontFamily = BrandFontFamily.Roboto,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        letterSpacing = 0.01.sp,
    ),
    h3 = TextStyle(
        fontFamily = BrandFontFamily.Roboto,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        letterSpacing = 0.01.sp,
    ),
    copy = TextStyle(
        fontFamily = BrandFontFamily.Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        letterSpacing = 0.01.sp,
    ),
    boldCopy = TextStyle(
        fontFamily = BrandFontFamily.Roboto,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        letterSpacing = 0.01.sp,
    ),
    smallCopy = TextStyle(
        fontFamily = BrandFontFamily.Roboto,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        letterSpacing = 0.01.sp,
    ),
    button = TextStyle(
        fontFamily = BrandFontFamily.Roboto,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        letterSpacing = 0.01.sp,
    ),
)
