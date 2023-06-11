package com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.viewmodel.StocktakingListingViewModel
import com.kiienkoromaniuk.sunshineandroid.view.composable.BrandDialog
import com.kiienkoromaniuk.sunshineandroid.view.text.H2Text
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun StocktakingListingScreen(
    navController: NavController,
    stocktakingListingViewModel: StocktakingListingViewModel = hiltViewModel()
) {
    val state by stocktakingListingViewModel.stocktakingListingState.collectAsState()
    val stocktakingState by stocktakingListingViewModel.stocktaking.collectAsState(initial = null)
    LaunchedEffect(key1 = true, block = {
        stocktakingListingViewModel.getStocktaking()
    })
    Scaffold(
        backgroundColor = BrandTheme.colors.N100,
        topBar = {
            TopAppBar(
                title = {
                    HeaderText(
                        text = "Inwentaryzacje",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                elevation = BrandTheme.dimensions.none,
                backgroundColor = BrandTheme.colors.N100,
                modifier = Modifier
                    .background(BrandTheme.colors.N100)
                    .padding(end = BrandTheme.dimensions.normal),
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                // Box5
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    BrandTheme.colors.N100,
                                    BrandTheme.colors.N600,
                                ),
                            ),
                        )
                        .fillMaxWidth()
                        .height(34.dp),
                )
                // Box4
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(BrandTheme.colors.N100)
                        .fillMaxWidth()
                        .height(30.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_add_menu),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(bottom = BrandTheme.dimensions.normal)
                        .align(Alignment.BottomCenter)
                        .clickable {
                            stocktakingListingViewModel.changeDialogVisibility()
                        },
                )
            }
        },
    ) { paddingValues ->
        if(state.shouldShowDialog){
            BrandDialog(
                title = "Inwentaryzacja",
                house = state.house,
                room = state.room,
                onDismissRequest = stocktakingListingViewModel::changeDialogVisibility,
                onRightButtonClick = {
                    stocktakingListingViewModel.changeDialogVisibility()
                    navController.navigate("stocktaking")
                },
                onHouseChange = stocktakingListingViewModel::updateHouse,
                onRoomChange = stocktakingListingViewModel::updateRoom
            )
        }
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            content = {
                item {
                    H2Text(
                        text = "Poprzednie inwentaryzacje",
                        modifier = Modifier.padding(BrandTheme.dimensions.extraLarge),
                    )
                }
                when(val stocktakingResponse = stocktakingState) {
                    is State.Error -> {}
                    is State.Progress -> {}
                    is State.Success -> {
                        items(stocktakingResponse.response.orEmpty()) {
                            StocktakingItem(
                                onClick = { navController.navigate("itemdetails") },
                            )
                        }
                    }
                    null -> {}
                }
            },
        )
    }
}
