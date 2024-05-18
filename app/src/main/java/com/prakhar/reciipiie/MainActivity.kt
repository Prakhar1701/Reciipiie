package com.prakhar.reciipiie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.android.gms.auth.api.identity.Identity
import com.prakhar.reciipiie.navigation.ReciipiieNavigation
import com.prakhar.reciipiie.screens.login.GoogleAuthUiClient
import com.prakhar.reciipiie.ui.theme.ReciipiieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReciipiieTheme {
                ReciipiieApp(googleAuthUiClient)
            }
        }
    }

    @Composable
    fun ReciipiieApp(googleAuthUiClient: GoogleAuthUiClient) {

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReciipiieNavigation(googleAuthUiClient)
            }
        }
    }
}
