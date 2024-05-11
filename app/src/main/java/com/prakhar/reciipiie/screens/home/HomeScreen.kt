package com.prakhar.reciipiie.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController, viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Text(text = "Home Screen")
}