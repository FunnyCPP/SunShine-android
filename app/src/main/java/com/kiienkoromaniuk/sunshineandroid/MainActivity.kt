package com.kiienkoromaniuk.sunshineandroid

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import com.kiienkoromaniuk.sunshineandroid.data.model.AccessTokenValidity
import com.kiienkoromaniuk.sunshineandroid.ui.barcode.composable.BarcodeScannerScreen
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable.AddItemScreen
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.helper.DateHelper
import com.kiienkoromaniuk.sunshineandroid.ui.itemdatails.composable.ItemDetailsScreen
import com.kiienkoromaniuk.sunshineandroid.ui.login.composable.LoginScreen
import com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable.MainScreen
import com.kiienkoromaniuk.sunshineandroid.ui.stocktaking.composable.StocktakingScreen
import com.kiienkoromaniuk.sunshineandroid.ui.stocktakinglisting.composable.StocktakingListingScreen
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPermissionsApi
@ExperimentalGetImage
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
                        }
                    )) {backStackEntry->
                        ItemDetailsScreen(
                            navController = navController,
                            itemId = backStackEntry.arguments?.getLong("id") ?: -1
                        )
                    }
                    composable("barcodescanner") { BarcodeScannerScreen(navController = navController) }
                    composable("stocktakinglisting") { StocktakingListingScreen(navController = navController)}
                    composable("stocktaking")  { StocktakingScreen(navController = navController) }
                    composable("login") { LoginScreen(navController = navController)}
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
        return when(mainActivityViewModel.isTokenValid()){
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
}
