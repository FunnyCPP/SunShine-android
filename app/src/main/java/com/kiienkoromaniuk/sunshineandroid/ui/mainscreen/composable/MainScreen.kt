package com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.viewmodel.MainScreenViewModel
import com.kiienkoromaniuk.sunshineandroid.view.composable.MenuItem
import com.kiienkoromaniuk.sunshineandroid.view.composable.TopBar
import com.kiienkoromaniuk.sunshineandroid.view.text.H2Text
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = true, block = {
        mainScreenViewModel.getItems()
        mainScreenViewModel.getBootstrap()
    })
    val itemsState by mainScreenViewModel.items.collectAsState(initial = null)
    val bootstrapState by mainScreenViewModel.bootstrap.collectAsState(initial = null)
    var isMenuExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        backgroundColor = BrandTheme.colors.N100,
        topBar = {
            TopBar(
                title = "Inwentaryzacja",
                modifier = Modifier
                    .background(BrandTheme.colors.N100)
                    .padding(end = BrandTheme.dimensions.normal),
                shouldShowMenu = true,
                isMenuExpanded = isMenuExpanded,
                onMenuExpandChange = {isMenuExpanded = it},
                menuItems = {
                    DropdownMenuItem(
                        onClick = {
                            mainScreenViewModel.logout()
                            navController.navigate("login")
                        },
                    ) {
                        MenuItem(
                            title = "Wyloguj się",
                            painter = painterResource(id = R.drawable.ic_leave),
                        )
                    }
                    DropdownMenuItem(
                        onClick = {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://barcode.tec-it.com/en"))
                            startActivity(context, browserIntent, null)
                        },
                    ) {
                        MenuItem(
                            title = "Wygeneruj kod",
                            painter = painterResource(id = R.drawable.ic_qr_code),
                        )
                    }
                }
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
                    when (val bootstrap = bootstrapState) {
                        is State.Error -> {}
                        is State.Progress -> {}
                        is State.Success -> {
                            HeaderSection(
                                itemsCount = bootstrap.response?.itemsCount ?: 0,
                                stocktakingCount = bootstrap.response?.stocktakingCount ?: 0,
                                onInventoryClick = { navController.navigate("stocktakinglisting") },
                            )
                        }
                        null -> {}
                    }
                }
                item {
                    H2Text(
                        text = "Dodane przedmioty",
                        modifier = Modifier.padding(BrandTheme.dimensions.extraLarge),
                    )
                }
                when (val items = itemsState) {
                    is State.Error -> {}
                    is State.Progress -> {}
                    is State.Success -> {
                        items(items.response?.items.orEmpty()) { item ->
                            Item(
                                item = item,
                                onClick = { navController.navigate("itemdetails?id=${item.id}") },
                            )
                        }
                    }
                    null -> {}
                }
            },
        )
    }
}
