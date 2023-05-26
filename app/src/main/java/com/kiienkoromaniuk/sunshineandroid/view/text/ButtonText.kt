package com.kiienkoromaniuk.sunshineandroid.view.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Composable
fun ButtonText(
    modifier: Modifier = Modifier,
    color: Color = BrandTheme.colors.N000,
    fontFamily: FontFamily? = BrandTheme.typography.button.fontFamily,
    fontWeight: FontWeight? = BrandTheme.typography.button.fontWeight,
    fontSize: TextUnit = BrandTheme.typography.button.fontSize,
    letterSpacing: TextUnit = BrandTheme.typography.button.letterSpacing,
    textAlign: TextAlign = TextAlign.Center,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    text: String?,
) {
    Text(
        modifier = modifier,
        color = color,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        letterSpacing = letterSpacing,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
        onTextLayout = onTextLayout,
        text = text.orEmpty(),
    )
}