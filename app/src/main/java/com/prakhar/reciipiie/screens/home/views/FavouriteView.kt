package com.prakhar.reciipiie.screens.home.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.prakhar.reciipiie.model.Recipe
import com.prakhar.reciipiie.navigation.ReciipiieScreens
import com.prakhar.reciipiie.screens.home.HomeScreenViewModel
import com.prakhar.reciipiie.screens.login.UserData

@Composable
fun FavouriteView(
    navController: NavController, viewModel: HomeScreenViewModel, userData: UserData?
) {

    val userId = userData?.userId.toString()

    viewModel.getUserFavouritesFromFirestoreDatabase(userId)

    var favouriteRecipes: List<Recipe> = listOf()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 15.dp, start = 15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Text(text = "Favourite Recipes", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        if (viewModel.data.value.loading!!) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(top = 30.dp, bottom = 20.dp)
                )
            }

        } else if (viewModel.data.value.data.isNullOrEmpty()) {

            Text(text = "No favorite recipes found. Add some from the recipe detail screen \uD83D\uDE0A")

        } else {

            favouriteRecipes = viewModel.data.value.data!!

            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                items(favouriteRecipes) { recipe ->
                    FavouriteRecipeCard(recipe = recipe) {

                        val idString = recipe.id.toString()
                        navController.navigate(ReciipiieScreens.DetailScreen.name + "/$idString")
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
fun FavouriteRecipeCard(recipe: Recipe, onClick: () -> Unit = {}) {

    Surface(
        modifier = Modifier
            .height(115.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, Color.LightGray)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(115.dp),
                model = recipe.image,
                contentDescription = "Food Image",
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = recipe.title.toString(),
                    fontSize = 19.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Ready in ${recipe.readyInMinutes} min",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}
