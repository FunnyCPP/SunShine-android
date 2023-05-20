package com.kiienko_romaniuk.sunshine_android.view.theme.data

import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.kiienko_romaniuk.sunshine_android.view.theme.BrandTheme

@Immutable
object BrandCheckboxDefaults {
    val primaryColors: CheckboxColors
        @Composable
        get() = CheckboxDefaults.colors(
            checkedColor = BrandTheme.colors.N800,
            uncheckedColor = BrandTheme.colors.N800,
            checkmarkColor = BrandTheme.colors.N000,
            disabledColor = BrandTheme.colors.N600,
            disabledIndeterminateColor = BrandTheme.colors.N600,
        )
}
