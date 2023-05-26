package com.kiienkoromaniuk.sunshineandroid.view.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Composable
fun PrimaryOutlinedTextField(
    value: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    onValueChange: (String) -> Unit = {},
    placeholder: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
) {
    OutlinedTextField(
        readOnly = readOnly,
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = BrandTheme.colors.N800,
            focusedBorderColor = BrandTheme.colors.N600,
            unfocusedBorderColor = BrandTheme.colors.N600,
            errorBorderColor = BrandTheme.colors.R400,
            errorCursorColor = BrandTheme.colors.R400,
            disabledBorderColor = BrandTheme.colors.N600,
        ),
        trailingIcon = trailingIcon,
        modifier = modifier.fillMaxWidth(),
        placeholder = placeholder,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        isError = isError,
        singleLine = singleLine,
    )
}
