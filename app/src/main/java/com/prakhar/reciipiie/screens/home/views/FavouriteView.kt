package com.prakhar.reciipiie.screens.home.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prakhar.reciipiie.model.Recipe
import com.prakhar.reciipiie.screens.home.HomeScreenViewModel
import com.prakhar.reciipiie.screens.login.UserData

@Composable
fun FavouriteView(
    navController: NavController, viewModel: HomeScreenViewModel, userData: UserData?
) {

    val userId = userData?.userId.toString()

    viewModel.getUserFavouritesFromFirestoreDatabase(userId)

    var favouriteRecipes: List<Recipe> = listOf()




    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 15.dp, start = 15.dp)
    ) {
        if (viewModel.data.value.loading == true) {

            CircularProgressIndicator(modifier = Modifier.size(80.dp))

        } else if (viewModel.data.value.data.isNullOrEmpty()) {

            Text(text = "No Favourite Found")

        } else {

            favouriteRecipes = viewModel.data.value.data!!
        }

        Column {
            favouriteRecipes.forEach { recipe ->
                Text(text = recipe.title.toString())
            }

        }
    }
}