package com.kiienkoromaniuk.sunshineandroid.ui.itemdatails.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.view.button.G400Button
import com.kiienkoromaniuk.sunshineandroid.view.extensions.clickable
import com.kiienkoromaniuk.sunshineandroid.view.extensions.topShadow
import com.kiienkoromaniuk.sunshineandroid.view.text.BoldCopyText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

private const val ACTION_BUTTON_WEIGHT = 1F

@ExperimentalFoundationApi
@Composable
fun ItemDetailsActionButtons(
    onBackPressed: () -> Unit,
    onSubmitClicked: () -> Unit,
    modifier: Modifier,
) {
    Surface(
        elevation = BrandTheme.dimensions.verySmall,
        modifier = modifier
            .padding(top = BrandTheme.dimensions.normal)
            .fillMaxWidth()
            .topShadow(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(
                    horizontal = BrandTheme.dimensions.extraLarge,
                    vertical = BrandTheme.dimensions.normal,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(ACTION_BUTTON_WEIGHT)
                    .clickable(
                        withIndication = false,
                        onClick = onBackPressed,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                BoldCopyText(
                    text = "Do zezłamowania",
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.width(BrandTheme.dimensions.normal))
            G400Button(
                radius = 2.dp,
                text = "Zezłamaj",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(ACTION_BUTTON_WEIGHT),
                onButtonClicked = onSubmitClicked,
            )
        }
    }
}
