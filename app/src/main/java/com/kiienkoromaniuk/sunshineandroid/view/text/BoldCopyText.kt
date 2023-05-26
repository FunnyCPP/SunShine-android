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
import androidx.compose.ui.unit.sp
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

private const val ADDITIONAL_LINE_SPACING = 3

@Composable
fun BoldCopyText(
    modifier: Modifier = Modifier,
    color: Color = BrandTheme.colors.N800,
    fontFamily: FontFamily? = BrandTheme.typography.boldCopy.fontFamily,
    fontWeight: FontWeight? = BrandTheme.typography.boldCopy.fontWeight,
    fontSize: TextUnit = BrandTheme.typography.boldCopy.fontSize,
    letterSpacing: TextUnit = BrandTheme.typography.boldCopy.letterSpacing,
    textAlign: TextAlign = TextAlign.Start,
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
        lineHeight = (fontSize.value + ADDITIONAL_LINE_SPACING).sp,
    )
}
