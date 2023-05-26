package com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiienkoromaniuk.sunshineandroid.view.button.N800Button
import com.kiienkoromaniuk.sunshineandroid.view.extensions.bottomShadow
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.H1Text
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

private const val DIVIDER = "/"

@Composable
fun HeaderSection(
    onInventoryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        elevation = BrandTheme.dimensions.verySmall,
        color = BrandTheme.colors.N100,
        modifier = modifier.bottomShadow(),
    ) {
        Column(
            modifier = Modifier
                .padding(BrandTheme.dimensions.extraLarge),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                H1Text(
                    text = "10 inw.",
                )
                CopyText(
                    text = DIVIDER + "100 przedm.",
                    color = BrandTheme.colors.N500,
                    fontSize = BrandTheme.typography.h1.fontSize,
                )
            }
            CopyText(
                text = "Możesz zobaczyć poprzednie inwentaryzacje oraz rozpocząć nową klikająć na przycisk poniżej",
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = BrandTheme.dimensions.verySmall),
            )
            N800Button(
                text = "Inwentaryzacje",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = BrandTheme.dimensions.normal),
                radius = 100.dp,
                onButtonClicked = onInventoryClick,
            )
        }
    }
}
