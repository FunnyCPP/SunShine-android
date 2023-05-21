package com.kiienkoromaniuk.sunshineandroid

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable.AddItemScreen
import com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable.MainScreen
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrandTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "mainscreen") {
                    composable("mainscreen") { MainScreen(navController) }
                    composable("additem") { AddItemScreen() }
                }
            }
        }
    }

    private fun showDatePicker() {
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePicker.show(this.supportFragmentManager, "Tag")
    }
}
