package com.prakhar.reciipiie.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.prakhar.reciipiie.R
import com.prakhar.reciipiie.navigation.ReciipiieScreens

@Composable
fun LoginScreen(
    navController: NavHostController, viewModel: LoginScreenViewModel = viewModel()
) {
    LoginScreenUI { navController.navigate(ReciipiieScreens.HomeScreen.name) }
}

@Preview
@Composable
private fun LoginScreenUI(onButtonClick: () -> Unit = {}) {
    Scaffold { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_background),
                contentDescription = "Login screen background image",
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "Welcome to Reciipiie",
                    color = Color.White,
                    fontSize = 65.sp,
                    lineHeight = 70.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = "Please signing to continue",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { onButtonClick() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE54900), contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google_icon),
                        contentDescription = "Google icon",
                        modifier = Modifier
                            .size(23.dp)
                            .padding(end = 5.dp)
                    )
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Continue with google",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
