package com.kiienkoromaniuk.sunshineandroid.ui.stocktakingdetails.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable.Item
import com.kiienkoromaniuk.sunshineandroid.ui.stocktakingdetails.viewmodel.StocktakingDetailsViewModel
import com.kiienkoromaniuk.sunshineandroid.view.extensions.bottomShadow
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.H1Text
import com.kiienkoromaniuk.sunshineandroid.view.text.H2Text
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun StocktakingDetailsScreen(
    navController: NavController,
    stocktakingId: Long,
    stocktakingDetailsViewModel: StocktakingDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true, block = {
        stocktakingDetailsViewModel.getStocktaking(stocktakingId)
    })
    val stocktakingResponse by stocktakingDetailsViewModel.stocktaking.collectAsState(initial = null)
    Scaffold(
        backgroundColor = BrandTheme.colors.N100,
        topBar = {
            TopAppBar(
                title = {
                    HeaderText(
                        text = "Inwentaryzacja",
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
    ) { paddingValues ->
        LazyColumn(
            content = {
                item {
                    Surface(
                        elevation = BrandTheme.dimensions.verySmall,
                        color = BrandTheme.colors.N100,
                        modifier = Modifier.bottomShadow(),
                    ) {
                        when (val stocktaking = stocktakingResponse) {
                            is State.Error -> {
                            }
                            is State.Progress -> {
                            }
                            is State.Success -> {
                                Column(
                                    modifier = Modifier
                                        .padding(BrandTheme.dimensions.extraLarge),
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        H1Text(
                                            text = "Pokój: ${stocktaking.response?.room}",
                                        )
                                        CopyText(
                                            text = " Budynek: ${stocktaking.response?.house}",
                                            color = BrandTheme.colors.N500,
                                            fontSize = BrandTheme.typography.h1.fontSize,
                                        )
                                    }
                                }
                            }
                            null -> {
                            }
                        }
                    }
                }
                item {
                    H2Text(
                        text = "Dodane należące przedmioty",
                        modifier = Modifier.padding(
                            horizontal = BrandTheme.dimensions.extraLarge,
                            vertical = BrandTheme.dimensions.normal,
                        ),
                    )
                }
                item {
                    LazyRow(content = {
                        when (val stocktaking = stocktakingResponse) {
                            is State.Error -> {
                            }
                            is State.Progress -> {
                            }
                            is State.Success -> {
                                items(stocktaking.response?.requiredItems.orEmpty()) { item ->
                                    Item(
                                        item = item,
                                        onClick = { navController.navigate("itemdetails?id=${item.id}") },
                                    )
                                }
                            }
                            null -> {
                            }
                        }
                    })
                }
                item {
                    H2Text(
                        text = "Brakujące przedmioty",
                        modifier = Modifier.padding(
                            horizontal = BrandTheme.dimensions.extraLarge,
                            vertical = BrandTheme.dimensions.normal,
                        ),
                    )
                }
                item {
                    LazyRow(content = {
                        when (val stocktaking = stocktakingResponse) {
                            is State.Error -> {
                            }
                            is State.Progress -> {
                            }
                            is State.Success -> {
                                items(stocktaking.response?.missingItems.orEmpty()) { item ->
                                    Item(
                                        item = item,
                                        onClick = { navController.navigate("itemdetails?id=${item.id}") },
                                    )
                                }
                            }
                            null -> {
                            }
                        }
                    })
                }
                item {
                    H2Text(
                        text = "Przedmioty, które nie należą do pokoju",
                        modifier = Modifier.padding(
                            horizontal = BrandTheme.dimensions.extraLarge,
                            vertical = BrandTheme.dimensions.normal,
                        ),
                    )
                }
                item {
                    LazyRow(content = {
                        when (val stocktaking = stocktakingResponse) {
                            is State.Error -> {
                            }
                            is State.Progress -> {
                            }
                            is State.Success -> {
                                items(stocktaking.response?.additionalItems.orEmpty()) { item ->
                                    Item(
                                        item = item,
                                        onClick = { navController.navigate("itemdetails?id=${item.id}") },
                                    )
                                }
                            }
                            null -> {
                            }
                        }
                    })
                }
            },
        )
    }
}
