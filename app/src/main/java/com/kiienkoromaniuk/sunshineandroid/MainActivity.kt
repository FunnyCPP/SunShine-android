package com.kiienkoromaniuk.sunshineandroid

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable.AddItemScreen
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.helper.DateHelper
import com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable.MainScreen
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrandTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "mainscreen") {
                    composable("mainscreen") { MainScreen(navController) }
                    composable("additem") {
                        AddItemScreen(
                            navController = navController,
                            showDatePicker = this@MainActivity::showDatePicker,
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
}
