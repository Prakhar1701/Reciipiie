package com.prakhar.reciipiie.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.prakhar.reciipiie.R

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Scaffold { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = "Login screen background image",
                contentScale = ContentScale.FillHeight,
            )
        }
    }
}
