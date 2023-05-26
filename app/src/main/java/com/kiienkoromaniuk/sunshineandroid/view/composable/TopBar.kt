package com.kiienkoromaniuk.sunshineandroid.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    shouldShowMenu: Boolean = false,
    isMenuExpanded: Boolean = false,
    onMenuExpandChange: (Boolean) -> Unit = {},
    onNavigateBack: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    menuItems: @Composable () -> Unit = {},
) {
    val topAppBarModifier = if (shouldShowMenu) {
        modifier
            .fillMaxWidth()
    } else {
        modifier
            .fillMaxWidth()
            .padding(end = 50.dp)
    }
    TopAppBar(
        elevation = BrandTheme.dimensions.none,
        backgroundColor = BrandTheme.colors.N100,
        modifier = modifier,
        title = {
            HeaderText(
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = topAppBarModifier,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavigateBack()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        },
        actions = {
            if (shouldShowMenu) {
                Box {
                    IconButton(onClick = { onNavigateBack() }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_more),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onMenuExpandChange(!isMenuExpanded)
                            },
                        )
                    }
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = {
                            onMenuExpandChange(false)
                        },
                    ) {
                        menuItems()
                    }
                }
            }
            actions.invoke(this)
        },
    )
}
