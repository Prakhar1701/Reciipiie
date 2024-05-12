package com.prakhar.reciipiie.screens.home

import com.prakhar.reciipiie.components.ReciipiieTabView
import com.prakhar.reciipiie.components.TabBarItem
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prakhar.reciipiie.screens.home.views.FavouriteView
import com.prakhar.reciipiie.screens.home.views.HomeView

@Composable
fun HomeScreen(
    navController: NavHostController, viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val homeTab = TabBarItem(
        title = "Home", selectedIcon = Icons.Filled.Home, unselectedIcon = Icons.Outlined.Home
    )

    val favouriteTab = TabBarItem(
        title = "Favourite",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
    )

    val tabBarItems = listOf(homeTab, favouriteTab)

    val homeNavController = rememberNavController()


    Scaffold(bottomBar = { ReciipiieTabView(tabBarItems, homeNavController) }) { contentPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            NavHost(navController = homeNavController, startDestination = homeTab.title) {
                composable(homeTab.title) {
                    HomeView()
                }
                composable(favouriteTab.title) {
                    FavouriteView()
                }
            }
        }
    }
}
