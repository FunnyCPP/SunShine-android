package com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable

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
import androidx.compose.material.Scaffold
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
import com.kiienkoromaniuk.sunshineandroid.view.text.H2Text
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    navController: NavController,
) {
    Scaffold(
        backgroundColor = BrandTheme.colors.N100,
        topBar = {
            TopAppBar(
                title = {
                    HeaderText(
                        text = "SunShine",
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
                            // TODO
                            navController.navigate("additem")
                        },
                )
            }
        },
    ) { paddingValues ->
        LazyColumn(
            content = {
                item {
                    HeaderSection(onInventoryClick = { /*TODO*/ })
                }
                item {
                    H2Text(
                        text = "Dodane przedmioty",
                        modifier = Modifier.padding(BrandTheme.dimensions.extraLarge),
                    )
                }
                items(3) {
                    Item(
                        item = com.kiienkoromaniuk.sunshineandroid.data.model.Item(
                            title = "Krzesło",
                            room = "3/20",
                            house = "34",
                            purchasingDate = "12-10-2022",
                            scrappingDate = "nie zezłamowany",
                            description = "Opis",
                        ),
                        onClick = { navController.navigate("itemdetails") },
                    )
                }
            },
        )
    }
}
