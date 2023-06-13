package com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kiienkoromaniuk.sunshineandroid.data.model.Stocktaking
import com.kiienkoromaniuk.sunshineandroid.view.extensions.clickable
import com.kiienkoromaniuk.sunshineandroid.view.text.BoldCopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun StocktakingItem(
    stocktaking: Stocktaking,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = BrandTheme.dimensions.extraLarge,
                vertical = BrandTheme.dimensions.small,
            )
            .background(color = BrandTheme.colors.N000, shape = RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .clickable(
                withIndication = false,
                onClick = onClick,
            ),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = BrandTheme.dimensions.extraLarge)
                .fillMaxWidth(),

        ) {
            Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
            Row {
                BoldCopyText(text = "Budynek:")
                Spacer(modifier = Modifier.width(BrandTheme.dimensions.extraSmall))
                CopyText(text = stocktaking.house)
                Spacer(modifier = Modifier.width(BrandTheme.dimensions.normal))
                BoldCopyText(text = "Pokój:")
                Spacer(modifier = Modifier.width(BrandTheme.dimensions.extraSmall))
                CopyText(text = stocktaking.room)
            }
            Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
            BoldCopyText(text = "Data inwentaryzacji:")
            CopyText(text = stocktaking.createdAt)
            Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
        }
    }
}
