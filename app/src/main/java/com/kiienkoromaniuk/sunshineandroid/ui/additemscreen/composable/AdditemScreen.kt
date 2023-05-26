package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.state.AddItemState
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.viewmodel.AddItemViewModel
import com.kiienkoromaniuk.sunshineandroid.view.composable.TopBar
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.text.PrimaryOutlinedTextField
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun AddItemScreen(
    showDatePicker: (onDateSelected: (String) -> Unit) -> Unit,
    navController: NavController,
    addItemViewModel: AddItemViewModel = viewModel(),
) {
    val addItemState by addItemViewModel.addItemState.collectAsState(initial = AddItemState())
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.padding(bottom = 70.dp),
            ) {
                TopBar(
                    title = "Nowy przedmiot",
                    onNavigateBack = navController::navigateUp,
                    modifier = Modifier
                        .background(BrandTheme.colors.N100)
                        .padding(end = BrandTheme.dimensions.normal),
                )
                ItemCreator(
                    addItemState = addItemState,
                    addItemViewModel = addItemViewModel,
                    showDatePicker = showDatePicker,
                )
            }
            ActionButtons(
                onBackPressed = navController::navigateUp,
                onSubmitClicked = {
                    // TODO
                },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun ItemCreator(
    addItemState: AddItemState,
    addItemViewModel: AddItemViewModel,
    showDatePicker: (onDateSelected: (String) -> Unit) -> Unit,
) {
    Column(
        modifier = Modifier.padding(20.dp),
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Nazwa inwentarza")
        Spacer(modifier = Modifier.height(5.dp))
        PrimaryOutlinedTextField(
            placeholder = {
                CopyText(
                    text = stringResource(R.string.nazwa_label),
                    color = BrandTheme.colors.N500,
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            value = addItemState.title.orEmpty(),
            onValueChange = addItemViewModel::updateTitle,
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Nr pokoju")
        Spacer(modifier = Modifier.height(5.dp))
        PrimaryOutlinedTextField(
            placeholder = {
                CopyText(
                    text = stringResource(R.string.nr_pokoju_label),
                    color = BrandTheme.colors.N500,
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            value = addItemState.room.orEmpty(),
            onValueChange = addItemViewModel::updateRoom,
        )
        Spacer(modifier = Modifier.height(15.dp))
        DatePicker(
            title = "Data zakupu",
            date = addItemState.purchasingDate.orEmpty(),
            showDatePicker = {
                showDatePicker { date ->
                    addItemViewModel.updatePurchasingDate(date)
                }
            },
        )
        Spacer(modifier = Modifier.height(15.dp))
        DatePicker(
            title = "Data złomowania",
            date = addItemState.scrappingDate.orEmpty(),
            showDatePicker = {
                showDatePicker { date ->
                    addItemViewModel.updateScrappingDate(date)
                }
            },
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Opis")
        Spacer(modifier = Modifier.height(5.dp))
        PrimaryOutlinedTextField(
            placeholder = {
                CopyText(
                    text = stringResource(R.string.opis_label),
                    color = BrandTheme.colors.N500,
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            value = addItemState.description.orEmpty(),
            onValueChange = addItemViewModel::updateDescription,
        )
    }
}
