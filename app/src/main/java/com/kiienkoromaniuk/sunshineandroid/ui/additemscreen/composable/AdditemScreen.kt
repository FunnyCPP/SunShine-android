package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.state.AddItemState
import com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.viewmodel.AddItemViewModel
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText

@Composable
fun AddItemScreen(
    addItemViewModel: AddItemViewModel = viewModel(),
) {
    val addItemState by addItemViewModel.addItemState.collectAsState(initial = AddItemState())
    Column(modifier = Modifier.padding(20.dp)) {
        HeaderText(text = "Pokój")
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = addItemState.room.orEmpty(),
            onValueChange = addItemViewModel::updateRoom,
        )
    }
}
