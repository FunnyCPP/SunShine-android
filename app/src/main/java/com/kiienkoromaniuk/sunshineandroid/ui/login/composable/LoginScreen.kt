package com.kiienkoromaniuk.sunshineandroid.ui.login.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.kiienkoromaniuk.sunshineandroid.R
import com.kiienkoromaniuk.sunshineandroid.data.State
import com.kiienkoromaniuk.sunshineandroid.ui.login.state.LoginState
import com.kiienkoromaniuk.sunshineandroid.ui.login.viewmodel.LoginViewModel
import com.kiienkoromaniuk.sunshineandroid.view.button.N800Button
import com.kiienkoromaniuk.sunshineandroid.view.composable.TopBar
import com.kiienkoromaniuk.sunshineandroid.view.text.CopyText
import com.kiienkoromaniuk.sunshineandroid.view.text.HeaderText
import com.kiienkoromaniuk.sunshineandroid.view.text.PrimaryOutlinedTextField
import com.kiienkoromaniuk.sunshineandroid.view.theme.BrandTheme

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val loginState by loginViewModel.loginState.collectAsState(initial = LoginState())
    val refreshTokenResponseState by loginViewModel.refreshTokenResponse.collectAsStateWithLifecycle(
        initialValue = null
    )
    val refreshTokenResponseStateFromRegister by loginViewModel.refreshTokenResponseFromRegister.collectAsStateWithLifecycle(
        initialValue = null
    )
    DisposableEffect(key1 = refreshTokenResponseState, effect = {
        when (val refreshTokenResponse = refreshTokenResponseState) {
            is State.Error -> {
                Toast.makeText(context, "Wystąpił błąd podczas logowania", Toast.LENGTH_LONG).show()
            }
            is State.Progress -> {

            }
            is State.Success -> {
                refreshTokenResponse.response?.let {
                    loginViewModel.saveRefreshToken(refreshTokenResponse.response)
                    navController.navigate("mainscreen")
                }

            }
            null -> {}
        }
        onDispose {  }
    })
    DisposableEffect(key1 = refreshTokenResponseStateFromRegister, effect = {
        when (val refreshTokenResponse = refreshTokenResponseStateFromRegister) {
            is State.Error -> {
                Toast.makeText(context, "Wystąpił błąd podczas logowania", Toast.LENGTH_LONG).show()
            }
            is State.Progress -> {

            }
            is State.Success -> {
                refreshTokenResponse.response?.let {
                    loginViewModel.saveRefreshToken(refreshTokenResponse.response)
                    navController.navigate("mainscreen")
                }

            }
            null -> {}
        }
        onDispose {  }
    })
    Scaffold(
        topBar = {
            TopBar(
                title = "Logowanie",
                onNavigateBack = navController::navigateUp,
                modifier = Modifier
                    .background(BrandTheme.colors.N100)
                    .padding(end = BrandTheme.dimensions.normal),
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 70.dp),
            ) {
                HeaderText(text = "Email:")
                Spacer(modifier = Modifier.height(5.dp))
                PrimaryOutlinedTextField(
                    placeholder = {
                        CopyText(
                            text = "Email",
                            color = BrandTheme.colors.N500,
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    value = loginState.email.orEmpty(),
                    onValueChange = loginViewModel::updateEmail,
                )
                Spacer(modifier = Modifier.height(15.dp))
                HeaderText(text = "Hasło:")
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
                    value = loginState.password.orEmpty(),
                    onValueChange = loginViewModel::updatePassword,
                )
                Spacer(modifier = Modifier.height(15.dp))
                N800Button(
                    text = "Zaloguj się",
                    onButtonClicked = loginViewModel::login,
                    modifier = Modifier.fillMaxWidth(),
                    radius = 20.dp
                )
                Spacer(modifier = Modifier.height(15.dp))
                HeaderText(text = "albo", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(15.dp))
                N800Button(
                    text = "Zarejestruj się",
                    onButtonClicked = loginViewModel::register,
                    modifier = Modifier.fillMaxWidth(),
                    radius = 20.dp
                )
            }
        }
    }
}