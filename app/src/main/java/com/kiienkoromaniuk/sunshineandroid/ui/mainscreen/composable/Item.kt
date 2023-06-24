package com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiienkoromaniuk.sunshineandroid.data.model.Item
import com.kiienkoromaniuk.sunshineandroid.view.extensions.clickable
import com.kiienkoromaniuk.sunshineandroid.view.text.BoldCopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun Item(
    item: Item,
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    bottom = BrandTheme.dimensions.normal,
                ),
            ) {
                BoldCopyText(
                    text = item.title.orEmpty(),
                    fontSize = 18.sp,

                )
            }
            Row {
                BoldCopyText(text = "Budynek:")
                Spacer(modifier = Modifier.width(BrandTheme.dimensions.extraSmall))
                CopyText(text = item.house)
                Spacer(modifier = Modifier.width(BrandTheme.dimensions.normal))
                BoldCopyText(text = "Pokój:")
                Spacer(modifier = Modifier.width(BrandTheme.dimensions.extraSmall))
                CopyText(text = item.room)
            }
            Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
            BoldCopyText(text = "Data zakupu:")
            CopyText(text = item.purchasingDate)
            Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
            BoldCopyText(text = "Opis:")
            CopyText(text = item.description)
            Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
        }
    }
}
