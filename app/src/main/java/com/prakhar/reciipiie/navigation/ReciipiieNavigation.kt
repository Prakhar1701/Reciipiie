package com.prakhar.reciipiie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prakhar.reciipiie.screens.detail.DetailScreen
import com.prakhar.reciipiie.screens.home.HomeScreen
import com.prakhar.reciipiie.screens.login.GoogleAuthUiClient
import com.prakhar.reciipiie.screens.login.LoginScreen

@Composable
fun ReciipiieNavigation(googleAuthUiClient: GoogleAuthUiClient) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ReciipiieScreens.LoginScreen.name) {

        composable(ReciipiieScreens.LoginScreen.name) {
            LoginScreen(navController = navController, googleAuthUiClient = googleAuthUiClient)
        }

        composable(ReciipiieScreens.HomeScreen.name) {
            HomeScreen(navController = navController, googleAuthUiClient = googleAuthUiClient)
        }

        composable(
            "${ReciipiieScreens.DetailScreen.name}/{id}", arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("id").let { id ->
                DetailScreen(navController = navController, recipeIdString = id.toString())
            }
        }
    }
}
