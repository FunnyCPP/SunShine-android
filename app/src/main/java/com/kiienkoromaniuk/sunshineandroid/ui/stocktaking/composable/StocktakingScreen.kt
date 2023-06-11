package com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.data.model.Item
import com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable.Item
import com.kiienkoromaniuk.sunshineandroid.view.button.G400Button
import com.kiienkoromaniuk.sunshineandroid.view.button.N800Button
import com.kiienkoromaniuk.sunshineandroid.view.extensions.bottomShadow
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.H1Text
import com.kiienkoromaniuk.sunshineandroid.view.text.H2Text
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun StocktakingScreen(
    navController: NavController,
) {
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
                                    text = "Pokój: 3/23",
                                )
                                CopyText(
                                    text = " Budynek: 34",
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
                                onButtonClicked = { },
                            )
                        }
                    }
                }
                item {
                    H2Text(
                        text = "Pozostałe przedmioty do zeskanowania",
                        modifier = Modifier.padding(
                            horizontal = BrandTheme.dimensions.extraLarge,
                            vertical = BrandTheme.dimensions.normal
                        ),
                    )
                }
                item {
                    LazyRow(content = {
                        items(10) {
                            Item(
                                item = Item(
                                    title = "Krzesło",
                                    room = "3/20",
                                    house = "34",
                                    purchasingDate = "12-10-2022",
                                    scrappingDate = "nie zezłamowany",
                                    description = "Opis",
                                    code = "",
                                    id = 1
                                ),
                                onClick = { navController.navigate("itemdetails") },
                            )
                        }
                    })
                }
                item {
                    H2Text(
                        text = "Zeskanowane przedmioty",
                        modifier = Modifier.padding(
                            horizontal = BrandTheme.dimensions.extraLarge,
                            vertical = BrandTheme.dimensions.normal
                        ),
                    )
                }
                item {
                    LazyRow(content = {
                        items(3) {
                            Item(
                                item = Item(
                                    title = "Krzesło",
                                    room = "3/20",
                                    house = "34",
                                    purchasingDate = "12-10-2022",
                                    scrappingDate = "nie zezłamowany",
                                    description = "Opis",
                                    code = "",
                                    id = 1,
                                ),
                                onClick = { navController.navigate("itemdetails") },
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
