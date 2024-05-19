package com.prakhar.reciipiie.screens.detail

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.prakhar.reciipiie.components.InfoDisplay1
import com.prakhar.reciipiie.components.clearString
import com.prakhar.reciipiie.model.Recipe
import com.prakhar.reciipiie.screens.login.GoogleAuthUiClient

@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    recipeIdString: String,
    googleAuthUiClient: GoogleAuthUiClient
) {
    val userId = googleAuthUiClient.getSignedInUser()?.userId.toString()

    val recipeId = recipeIdString.toInt()

    viewModel.getRecipeInformation(recipeId)

    if (viewModel.isLoadingRecipeInformation) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(70.dp))
        }

    } else if (viewModel.isSuccessRecipeInformation) {

        val recipe = viewModel.recipeInformation!!

        Surface(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                AsyncImage(
                    modifier = Modifier.height(400.dp),
                    model = recipe.image,
                    contentDescription = "Food Image",
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(50.dp))

                DetailScreenUI(recipe)
            }

            Box(
                modifier = Modifier
                    .padding(top = 50.dp, end = 20.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ) {
                FloatingActionButton(contentColor = Color.Red,
                    containerColor = Color.White,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(),
                    onClick = {
                        viewModel.isFavourite = !viewModel.isFavourite
                        viewModel.addRemoveFavourite(userId, recipe)
                    }) {
                    val imageVector =
                        if (viewModel.isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder

                    Icon(
                        imageVector = imageVector,
                        contentDescription = "Favourite, Floating Button",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    } else {
        Text(
            text = "Something went wrong, unable to load recipe details\u2757",
            modifier = Modifier.padding(100.dp)
        )
    }
}

@Composable
private fun DetailScreenUI(recipe: Recipe) {

    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoDisplay1(topStr = "Ready in", bottomStr = recipe.readyInMinutes.toString())
        InfoDisplay1(topStr = "Servings", bottomStr = recipe.servings.toString())
        InfoDisplay1(topStr = "Price/serving", bottomStr = recipe.pricePerServing.toString())
    }

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Text(text = "Instructions", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = recipe.instructions!!.clearString(), color = Color.Gray)

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Quick Summary", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = recipe.summary!!.clearString(), color = Color.Gray)
    }
}
