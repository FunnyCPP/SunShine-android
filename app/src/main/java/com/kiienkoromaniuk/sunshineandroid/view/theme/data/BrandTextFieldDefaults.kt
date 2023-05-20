package com.kiienkoromaniuk.sunshineandroid.view.theme.data

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Immutable
object BrandTextFieldDefaults {
    val grayOutlinedTextFieldColors: TextFieldColors
        @Composable
        get() = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BrandTheme.colors.N600,
            cursorColor = BrandTheme.colors.N600,
            errorCursorColor = BrandTheme.colors.R400,
            focusedBorderColor = BrandTheme.colors.N600,
            unfocusedBorderColor = BrandTheme.colors.N600,
            errorBorderColor = BrandTheme.colors.R400,
            focusedLabelColor = BrandTheme.colors.N600,
            unfocusedLabelColor = BrandTheme.colors.N600,
            errorLabelColor = BrandTheme.colors.R400,
            placeholderColor = BrandTheme.colors.N600,
        )

    val primaryOutlinedTextFieldColors: TextFieldColors
        @Composable
        get() = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BrandTheme.colors.N800,
            cursorColor = BrandTheme.colors.N800,
            errorCursorColor = BrandTheme.colors.R400,
            focusedBorderColor = BrandTheme.colors.N800,
            unfocusedBorderColor = BrandTheme.colors.N800,
            errorBorderColor = BrandTheme.colors.R400,
            focusedLabelColor = BrandTheme.colors.N800,
            unfocusedLabelColor = BrandTheme.colors.N800,
            errorLabelColor = BrandTheme.colors.R400,
            placeholderColor = BrandTheme.colors.N800,
        )

    val primaryLight3OutlinedTextFieldColors: TextFieldColors
        @Composable
        get() = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BrandTheme.colors.N800,
            cursorColor = BrandTheme.colors.N500,
            errorCursorColor = BrandTheme.colors.R400,
            focusedBorderColor = BrandTheme.colors.N500,
            unfocusedBorderColor = BrandTheme.colors.N500,
            errorBorderColor = BrandTheme.colors.R400,
            focusedLabelColor = BrandTheme.colors.N500,
            unfocusedLabelColor = BrandTheme.colors.N500,
            errorLabelColor = BrandTheme.colors.R400,
            placeholderColor = BrandTheme.colors.N500,
        )

    val primaryLight4OutlinedTextFieldColors: TextFieldColors
        @Composable
        get() = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BrandTheme.colors.N800,
            cursorColor = BrandTheme.colors.N300,
            errorCursorColor = BrandTheme.colors.R400,
            focusedBorderColor = BrandTheme.colors.N300,
            unfocusedBorderColor = BrandTheme.colors.N300,
            errorBorderColor = BrandTheme.colors.R400,
            focusedLabelColor = BrandTheme.colors.N300,
            unfocusedLabelColor = BrandTheme.colors.N300,
            errorLabelColor = BrandTheme.colors.R400,
            placeholderColor = BrandTheme.colors.N300,
        )
}
