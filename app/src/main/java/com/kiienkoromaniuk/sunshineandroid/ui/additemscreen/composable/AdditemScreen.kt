package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.state.AddItemState
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.viewmodel.AddItemViewModel
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText

@Composable
fun AddItemScreen(
    addItemViewModel: AddItemViewModel = viewModel(),
) {
    val addItemState by addItemViewModel.addItemState.collectAsState(initial = AddItemState())

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(
            text = "SunShine",
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            modifier = Modifier
                .fillMaxWidth(),
        )


        Text(
            text = "DODAJ nowy ŚT",
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(15.dp))

        HeaderText(text = "Nr inwentarza")
        OutlinedTextField(
            label = { Text(stringResource(R.string.numer_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            value = addItemState.lp.orEmpty(),
            onValueChange = addItemViewModel::updatelp,
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Nazwa inwentarza")
        OutlinedTextField(
            label = { Text(stringResource(R.string.nazwa_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            value = addItemState.nazwa.orEmpty(),
            onValueChange = addItemViewModel::updateNazwa,
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Nr pokoju")
        OutlinedTextField(
            label = { Text(stringResource(R.string.nr_pokoju_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
            value = addItemState.room.orEmpty(),
            onValueChange = addItemViewModel::updateRoom,
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Data zakupu")
        OutlinedTextField(
            label = { Text(stringResource(R.string.data_zakupu_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),
            value = addItemState.data_zakupu.orEmpty(),
            onValueChange = addItemViewModel::updateDataZakupu,
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Data złomowania")
        OutlinedTextField(
            label = { Text(stringResource(R.string.data_zakupu_label)) },
            singleLine = true,
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,imeAction = ImeAction.Next),

            value = addItemState.data_zlomowania.orEmpty(),
            onValueChange = addItemViewModel::updateDataZlomowania,
        )
        Spacer(modifier = Modifier.height(15.dp))
        HeaderText(text = "Opis")
        OutlinedTextField(
            label = { Text(stringResource(R.string.opis_label)) },
            keyboardOptions = KeyboardOptions(imeAction=ImeAction.Done),
            value = addItemState.opis.orEmpty(),
            onValueChange = addItemViewModel::updateOpis,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(stringResource(R.string.dodaj))
            }
            Spacer(modifier = Modifier.width(32.dp))

            Button(onClick = {/*TODO*/ }) {
                Text(stringResource(R.string.anuluj))
            }


        }
    }

}