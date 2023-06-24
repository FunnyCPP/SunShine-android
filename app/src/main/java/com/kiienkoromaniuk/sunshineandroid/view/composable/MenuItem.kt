package com.kiienkoromaniuk.sunshineandroid.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    title: String,
    painter: Painter,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                BrandTheme.colors.R400,
            ),
            modifier = Modifier.padding(BrandTheme.dimensions.normal),
        )
        CopyText(
            fontSize = BrandTheme.typography.smallCopy.fontSize,
            text = title,
            color = BrandTheme.colors.R400,
        )
    }
}