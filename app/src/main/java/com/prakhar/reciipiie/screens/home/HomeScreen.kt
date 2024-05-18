package com.prakhar.reciipiie.screens.home

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prakhar.reciipiie.components.ReciipiieTabView
import com.prakhar.reciipiie.components.TabBarItem
import com.prakhar.reciipiie.screens.home.views.FavouriteView
import com.prakhar.reciipiie.screens.home.views.HomeView
import com.prakhar.reciipiie.screens.login.GoogleAuthUiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    googleAuthUiClient: GoogleAuthUiClient
) {

    val userData = googleAuthUiClient.getSignedInUser()

    val context = LocalContext.current

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
                    HomeView(navController, viewModel, userData) {

                        GlobalScope.launch(Dispatchers.Main) {

                            googleAuthUiClient.signOut()

                            Toast.makeText(context, "Signed Out Successfully", Toast.LENGTH_LONG)
                                .show()

                            navController.popBackStack()
                        }
                    }
                }

                composable(favouriteTab.title) {
                    FavouriteView()
                }
            }
        }
    }
}
