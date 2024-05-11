package com.prakhar.reciipiie.screens.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun DetailScreen(
    navController: NavHostController, viewModel: DetailScreenViewModel = hiltViewModel()
) {
    Text(text = "Detail Screen")
}