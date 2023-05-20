package com.kiienkoromaniuk.sunshineandroid.ui.mainscreen.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.view.extensions.skeleton
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Composable
fun MainScreen() {
    Scaffold(
        backgroundColor = BrandTheme.colors.N100,
        topBar = {
            TopAppBar(
                elevation = BrandTheme.dimensions.none,
                backgroundColor = BrandTheme.colors.N100,
                title = {
                    HeaderText(
                        text = "Sunshine",
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                    )
                },
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    BrandTheme.colors.N000,
                                    BrandTheme.colors.N600,
                                ),
                            ),
                        )
                        .fillMaxWidth()
                        .height(34.dp),
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(BrandTheme.colors.N000)
                        .fillMaxWidth()
                        .height(30.dp),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_add_menu),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(bottom = BrandTheme.dimensions.normal)
                        .align(Alignment.BottomCenter)
                        .clickable {
                            // TODO
                        },
                )
            }
        },
    ) { paddingValues ->
        Column {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .background(BrandTheme.colors.N000)
                        .padding(20.dp)
                        .skeleton(20.dp)
                        .fillMaxWidth()
                        .height(160.dp),
                )
            }
        }
    }
}
