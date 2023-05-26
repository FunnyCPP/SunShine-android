package com.kiienkoromaniuk.sunshineandroid.view.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Composable
fun G400Button(
    text: String?,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    radius: Dp = 0.dp,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    isLoading: Boolean = false,
    onButtonClicked: () -> Unit = {},
) {
    BrandButton(
        text = text,
        modifier = modifier,
        isEnabled = isEnabled,
        radius = radius,
        textColor = BrandTheme.colors.N000,
        backgroundColor = BrandTheme.colors.G400,
        disabledBackgroundColor = BrandTheme.colors.G200,
        contentPadding = contentPadding,
        isLoading = isLoading,
        onButtonClicked = onButtonClicked,
    )
}
