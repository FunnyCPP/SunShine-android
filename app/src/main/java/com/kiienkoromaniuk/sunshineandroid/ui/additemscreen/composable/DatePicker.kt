package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsActions.CopyText
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.view.extensions.clickable
import com.kiienkoromaniuk.sunshineandroid.view.text.BoldCopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.PrimaryOutlinedTextField
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@ExperimentalFoundationApi
@Composable
fun DatePicker(
    title: String,
    date: String,
    showDatePicker: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoldCopyText(text = title)
    Box(
        modifier = modifier.clickable(
            withIndication = false,
            onClick = showDatePicker,
        ),
    ) {
        PrimaryOutlinedTextField(
            readOnly = true,
            value = date,
            placeholder = {
                CopyText(
                    text = stringResource(id = R.string.select_date),
                    color = BrandTheme.colors.N500,
                )
            },
            enabled = false,
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar_v3),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(BrandTheme.colors.N600),
                )
            },
            modifier = Modifier.padding(top = BrandTheme.dimensions.small),
        )
    }
}
