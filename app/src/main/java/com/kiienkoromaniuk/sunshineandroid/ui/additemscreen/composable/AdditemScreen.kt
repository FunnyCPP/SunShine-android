package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.state.AddItemState
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.viewmodel.AddItemViewModel
import com.kiienkoromaniuk.sunshineandroid.view.button.N800Button
import com.kiienkoromaniuk.sunshineandroid.view.composable.TopBar
import com.kiienkoromaniuk.sunshineandroid.view.extensions.GetOnceResult
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.text.PrimaryOutlinedTextField
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Composable
fun AddItemScreen(
    showDatePicker: (onDateSelected: (String) -> Unit) -> Unit,
    navController: NavController,
    addItemViewModel: AddItemViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val addItemState by addItemViewModel.addItemState.collectAsState(initial = AddItemState())
    val createItemResponse by addItemViewModel.createItemResponse.collectAsState(initial = null)
    navController.GetOnceResult<String>("barcode") {
        addItemViewModel.updateBarcode(it)
    }
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    if (permissionState.status != PermissionStatus.Granted) {
        LaunchedEffect(true) {
            permissionState.launchPermissionRequest()
        }
    }
    DisposableEffect(key1 = createItemResponse, effect = {
        when (createItemResponse) {
            is State.Error -> {
                Toast.makeText(context, "Wystąpił błąd podczas dodawania przedmiotu", Toast.LENGTH_LONG).show()
            }
            is State.Progress -> {}
            is State.Success -> {
                Toast.makeText(context, "Przedmiot został dodany", Toast.LENGTH_LONG).show()
                navController.navigate("mainscreen")
            }
            null -> {}
        }
        onDispose { }
    })
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.padding(bottom = 50.dp),
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
                    showBarcodeScanner = { navController.navigate("barcodescanner") },
                )
            }
            ActionButtons(
                onBackPressed = navController::navigateUp,
                onSubmitClicked = {
                    addItemViewModel.createItem()
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
    showBarcodeScanner: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
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
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            value = addItemState.room.orEmpty(),
            onValueChange = addItemViewModel::updateRoom,
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Nr budynku")
        Spacer(modifier = Modifier.height(5.dp))
        PrimaryOutlinedTextField(
            placeholder = {
                CopyText(
                    text = "Nr budynku",
                    color = BrandTheme.colors.N500,
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            value = addItemState.house.orEmpty(),
            onValueChange = addItemViewModel::updateHouse,
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
        addItemState.barcode?.let { barcode ->
            Spacer(modifier = Modifier.height(15.dp))
            HeaderText(text = "Barcode:")
            Spacer(modifier = Modifier.height(5.dp))
            PrimaryOutlinedTextField(
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                value = addItemState.barcode,
                onValueChange = addItemViewModel::updateBarcode,
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        N800Button(
            text = "Zeskanuj kod",
            radius = 20.dp,
            modifier = Modifier.fillMaxWidth(),
            onButtonClicked = showBarcodeScanner,
        )
    }
}
