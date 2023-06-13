package com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.composable

import android.Manifest
import android.widget.Toast
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable.Item
import com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.state.StocktakingState
import com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.viewmodel.StocktakingViewModel
import com.kiienkoromaniuk.sunshineandroid.view.button.G400Button
import com.kiienkoromaniuk.sunshineandroid.view.button.N800Button
import com.kiienkoromaniuk.sunshineandroid.view.extensions.bottomShadow
import com.kiienkoromaniuk.sunshineandroid.view.extensions.getOnceResult
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.H1Text
import com.kiienkoromaniuk.sunshineandroid.view.text.H2Text
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun StocktakingScreen(
    navController: NavController,
    house: String,
    room: String,
    stocktakingViewModel: StocktakingViewModel,
    stocktakingState: StocktakingState,
    addItem: (code: String) -> Unit,
    getItems: (house: String, room: String) -> Unit,
) {
    LaunchedEffect(key1 = true, block = {
        getItems(house, room)
        navController.getOnceResult<String>("barcode") {
            addItem(it)
        }
    })
    val context = LocalContext.current
    val stocktakingResponse by stocktakingViewModel.stocktaking.observeAsState()
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    if (permissionState.status != PermissionStatus.Granted) {
        LaunchedEffect(true) {
            permissionState.launchPermissionRequest()
        }
    }
    DisposableEffect(key1 = stocktakingResponse, effect = {
        when (stocktakingResponse) {
            is State.Error -> {
                Toast.makeText(context, "Dodanieinwentaryzacji nie powiodło się", Toast.LENGTH_LONG).show()
            }
            is State.Progress -> {}
            is State.Success -> {
                navController.navigateUp()
            }
            null -> {}
        }
        onDispose { }
    })
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
                        Column(
                            modifier = Modifier
                                .padding(BrandTheme.dimensions.extraLarge),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                H1Text(
                                    text = "Pokój: $room",
                                )
                                CopyText(
                                    text = " Budynek: $house",
                                    color = BrandTheme.colors.N500,
                                    fontSize = BrandTheme.typography.h1.fontSize,
                                )
                            }
                            N800Button(
                                text = "Zakoncz inwentaryzacje",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = BrandTheme.dimensions.normal),
                                radius = 100.dp,
                                onButtonClicked = {
                                    stocktakingViewModel.createStocktaking(house, room)
                                },
                            )
                        }
                    }
                }
                item {
                    H2Text(
                        text = "Pozostałe przedmioty do zeskanowania",
                        modifier = Modifier.padding(
                            horizontal = BrandTheme.dimensions.extraLarge,
                            vertical = BrandTheme.dimensions.normal,
                        ),
                    )
                }
                item {
                    LazyRow(content = {
                        items(stocktakingState.remainingItems) { item ->
                            Item(
                                item = item,
                                onClick = { navController.navigate("itemdetails?id=${item.id}") },
                            )
                        }
                    })
                }
                item {
                    H2Text(
                        text = "Zeskanowane przedmioty",
                        modifier = Modifier.padding(
                            horizontal = BrandTheme.dimensions.extraLarge,
                            vertical = BrandTheme.dimensions.normal,
                        ),
                    )
                }
                item {
                    LazyRow(content = {
                        items(stocktakingState.addedItems) { item ->
                            Item(
                                item = item,
                                onClick = { navController.navigate("itemdetails?id=${item.id}") },
                            )
                        }
                    })
                }
                item {
                    G400Button(
                        text = "Skanuj",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                all = BrandTheme.dimensions.extraLarge,

                            ),
                        radius = 100.dp,
                        onButtonClicked = {
                            navController.navigate("barcodescanner")
                        },
                    )
                }
            },
        )
    }
}
