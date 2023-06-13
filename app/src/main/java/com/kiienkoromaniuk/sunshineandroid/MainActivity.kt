package com.kiienkoromaniuk.sunshineandroid

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.data.model.AccessTokenValidity
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable.AddItemScreen
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.helper.DateHelper
import com.kiienkoromaniuk.sunshineandroid.ui.barcode.composable.BarcodeScannerScreen
import com.kiienkoromaniuk.sunshineandroid.ui.itemdatails.composable.ItemDetailsScreen
import com.kiienkoromaniuk.sunshineandroid.ui.login.composable.LoginScreen
import com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable.MainScreen
import com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.composable.StocktakingScreen
import com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.viewmodel.StocktakingViewModel
import com.kiienkoromaniuk.sunshineandroid.ui.stocktakingdetails.composable.StocktakingDetailsScreen
import com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.composable.StocktakingListingScreen
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
@ExperimentalGetImage
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    private val stocktakingViewModel by viewModels<StocktakingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()
        setContent {
            val stocktakingState by stocktakingViewModel.stocktakingState.collectAsState()
            BrandTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = getStartDestination()) {
                    composable("mainscreen") { MainScreen(navController) }
                    composable("additem") {
                        AddItemScreen(
                            navController = navController,
                            showDatePicker = this@MainActivity::showDatePicker,
                        )
                    }
                    composable(
                        route = "itemdetails?id={id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.LongType
                                defaultValue = -1
                            },
                        ),
                    ) { backStackEntry ->
                        ItemDetailsScreen(
                            navController = navController,
                            itemId = backStackEntry.arguments?.getLong("id") ?: -1,
                        )
                    }
                    composable("barcodescanner") { BarcodeScannerScreen(navController = navController) }
                    composable("stocktakinglisting") { StocktakingListingScreen(navController = navController) }
                    composable(
                        route = "stocktaking/{house}/{room}",
                        arguments = listOf(
                            navArgument("house") {
                                type = NavType.StringType
                                defaultValue = ""
                            },
                            navArgument("room") {
                                type = NavType.StringType
                                defaultValue = ""
                            },
                        ),
                    ) { backStackEntry ->
                        StocktakingScreen(
                            navController = navController,
                            house = backStackEntry.arguments?.getString("house") ?: "",
                            room = backStackEntry.arguments?.getString("room") ?: "",
                            stocktakingViewModel = stocktakingViewModel,
                            stocktakingState = stocktakingState,
                            addItem = this@MainActivity::addItem,
                            getItems = this@MainActivity::getItems,

                        )
                    }
                    composable("login") { LoginScreen(navController = navController) }
                    composable(
                        route = "stocktakingdetails?id={id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.LongType
                                defaultValue = -1
                            },
                        ),
                    ) { backStackEntry ->
                        StocktakingDetailsScreen(
                            navController = navController,
                            stocktakingId = backStackEntry.arguments?.getLong("id") ?: -1,
                        )
                    }
                }
            }
        }
    }

    private fun showDatePicker(
        onDateSelected: (date: String) -> Unit,
    ) {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setInputMode(INPUT_MODE_CALENDAR)
                .setTitleText("Select date")
                .build()
        datePicker.addOnPositiveButtonClickListener {
            onDateSelected(DateHelper.dateWithDashesFromMilliseconds(it).orEmpty())
        }
        datePicker.show(this.supportFragmentManager, "Tag")
    }

    private fun getStartDestination(): String {
        return when (mainActivityViewModel.isTokenValid()) {
            AccessTokenValidity.VALID -> {
                "mainscreen"
            }
            AccessTokenValidity.EXPIRED -> {
                "login"
            }
            AccessTokenValidity.INVALID -> {
                "login"
            }
        }
    }

    private fun addItem(code: String) {
        stocktakingViewModel.getItemByCode(code = code)
    }

    private fun getItems(house: String, room: String) {
        if (!stocktakingViewModel.isGetRequiredItemsWasCalled) {
            stocktakingViewModel.getItemsByHouseAndRoom(house = house, room = room)
            stocktakingViewModel.isGetRequiredItemsWasCalled = true
        }
    }

    private fun observeData() {
        stocktakingViewModel.requiredItemsState.observe(this) { state ->
            when (state) {
                is State.Error -> {
                }
                is State.Progress -> {
                }
                is State.Success -> {
                    stocktakingViewModel.updateRemainingItems(state.response?.items.orEmpty())
                    stocktakingViewModel.updateRequiredItems(state.response?.items.orEmpty())
                }
            }
        }
        stocktakingViewModel.itemState.observe(this) { state ->
            when (state) {
                is State.Error -> {
                }
                is State.Progress -> {
                }
                is State.Success -> {
                    state.response?.let { item ->
                        val stocktakingState = stocktakingViewModel.stocktakingState.value
                        val addedItems = stocktakingState.addedItems + item
                        val remainingItems = stocktakingState.remainingItems.toMutableList()
                        if (remainingItems.contains(item)) {
                            remainingItems.remove(item)
                            stocktakingViewModel.updateRemainingItems(remainingItems)
                        } else {
                            val additionalItems = stocktakingState.additionalItems + item
                            stocktakingViewModel.updateAdditionalItems(additionalItems)
                        }
                        stocktakingViewModel.updateAddedItems(addedItems)
                    }
                }
            }
        }
    }
}
