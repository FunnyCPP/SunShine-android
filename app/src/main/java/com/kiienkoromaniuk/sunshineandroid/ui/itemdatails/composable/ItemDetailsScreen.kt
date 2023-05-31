package com.kiienkoromaniuk.sunshineandroid.ui.itemdatails.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kiienkoromaniuk.sunshineandroid.view.composable.TopBar
import com.kiienkoromaniuk.sunshineandroid.view.text.BoldCopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun ItemDetailsScreen(
    navController: NavController,
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.padding(bottom = 70.dp),
            ) {
                TopBar(
                    title = "Szczegóły",
                    onNavigateBack = navController::navigateUp,
                    modifier = Modifier
                        .background(BrandTheme.colors.N100)
                        .padding(end = BrandTheme.dimensions.normal),
                )
                Column(
                    modifier = Modifier.padding(20.dp),
                ) {
                    BoldCopyText(text = "Krzesło")
                    Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                    BoldCopyText(text = "Budynek")
                    CopyText(text = "34")
                    Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                    BoldCopyText(text = "Pokój")
                    CopyText(text = "3/20")
                    Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                    BoldCopyText(text = "Data zakupu:")
                    CopyText(text = "12-10-2022")
                    Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                    BoldCopyText(text = "opis:")
                    CopyText(text = "Przykładowy opis")
                }
            }
            ItemDetailsActionButtons(
                onBackPressed = navController::navigateUp,
                onSubmitClicked = {
                    // TODO
                },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}
