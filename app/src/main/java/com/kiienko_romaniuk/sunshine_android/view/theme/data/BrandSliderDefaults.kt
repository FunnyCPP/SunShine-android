package com.kiienko_romaniuk.sunshine_android.view.theme.data

import androidx.compose.material.SliderColors
import androidx.compose.material.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.kiienko_romaniuk.sunshine_android.view.theme.BrandTheme

@Immutable
object BrandSliderDefaults {
    val colors: SliderColors
        @Composable
        get() = SliderDefaults.colors(
            thumbColor = BrandTheme.colors.B400,
            disabledThumbColor = BrandTheme.colors.N300,
            activeTrackColor = BrandTheme.colors.B400,
            inactiveTrackColor = BrandTheme.colors.N300,
            disabledActiveTrackColor = BrandTheme.colors.N300,
            disabledInactiveTrackColor = BrandTheme.colors.N300,
            activeTickColor = Color.Transparent,
            inactiveTickColor = Color.Transparent,
            disabledActiveTickColor = Color.Transparent,
            disabledInactiveTickColor = Color.Transparent,
        )
}
