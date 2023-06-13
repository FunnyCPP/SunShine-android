package com.kiienkoromaniuk.sunshineandroid.view.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.kiienkoromaniuk.sunshineandroid.view.button.N800Button
import com.kiienkoromaniuk.sunshineandroid.view.text.H2Text
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.text.PrimaryOutlinedTextField
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun BrandDialog(
    modifier: Modifier = Modifier,
    title: String?,
    house: String?,
    room: String?,
    onDismissRequest: () -> Unit,
    onRightButtonClick: () -> Unit,
    onHouseChange: (String) -> Unit,
    onRoomChange: (String) -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box {
            Column(
                modifier = modifier
                    .shadow(
                        1.dp,
                    )
                    .background(
                        color = BrandTheme.colors.N000,
                        shape = RoundedCornerShape(
                            BrandTheme.dimensions.verySmall,
                        ),
                    )
                    .padding(
                        BrandTheme.dimensions.extraLarge,
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    H2Text(
                        text = title,
                        color = BrandTheme.colors.N800,
                    )
                    Spacer(modifier = Modifier.height(BrandTheme.dimensions.extraLarge))
                    HeaderText(text = "Numer budynku")
                    Spacer(modifier = Modifier.height(5.dp))
                    PrimaryOutlinedTextField(
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        value = house.orEmpty(),
                        onValueChange = onHouseChange,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    HeaderText(text = "Numer pokoju")
                    Spacer(modifier = Modifier.height(5.dp))
                    PrimaryOutlinedTextField(
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        value = room.orEmpty(),
                        onValueChange = onRoomChange,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Spacer(modifier = Modifier.width(BrandTheme.dimensions.extraLarge))
                    N800Button(
                        text = "Rozpocznij",
                        onButtonClicked = onRightButtonClick,
                        contentPadding = PaddingValues(10.dp),
                        radius = 15.dp,
                    )
                }
            }
        }
    }
}
