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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.ui.itemdatails.viewmodel.ItemDetailsViewModel
import com.kiienkoromaniuk.sunshineandroid.view.composable.TopBar
import com.kiienkoromaniuk.sunshineandroid.view.text.BoldCopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun ItemDetailsScreen(
    navController: NavController,
    itemId: Long,
    itemDetailsViewModel: ItemDetailsViewModel = hiltViewModel(),
) {
    val itemState by itemDetailsViewModel.itemResponse.collectAsState(initial = null)
    LaunchedEffect(key1 = true, block = {
        itemDetailsViewModel.getItem(itemId)
    })
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
                when (val itemResponse = itemState) {
                    is State.Error -> {}
                    is State.Progress -> {}
                    is State.Success -> {
                        itemResponse.response?.let { item ->
                            Column(
                                modifier = Modifier.padding(20.dp),
                            ) {
                                BoldCopyText(text = item.title)
                                Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                                BoldCopyText(text = "Budynek")
                                CopyText(text = item.house)
                                Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                                BoldCopyText(text = "Pokój")
                                CopyText(text = item.room)
                                Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                                BoldCopyText(text = "Data zakupu:")
                                CopyText(text = item.purchasingDate)
                                Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                                BoldCopyText(text = "Data zezłamowania:")
                                CopyText(text = item.scrappingDate)
                                Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                                BoldCopyText(text = "Opis:")
                                CopyText(text = item.description)
                                Spacer(modifier = Modifier.height(BrandTheme.dimensions.normal))
                                BoldCopyText(text = "Kod:")
                                CopyText(text = item.code)
                            }
                        }
                    }
                    null -> {
                    }
                }
            }
            /*ItemDetailsActionButtons(
                onBackPressed = navController::navigateUp,
                onSubmitClicked = {
                    // TODO
                },
                modifier = Modifier.align(Alignment.BottomCenter),
            )*/
        }
    }
}
