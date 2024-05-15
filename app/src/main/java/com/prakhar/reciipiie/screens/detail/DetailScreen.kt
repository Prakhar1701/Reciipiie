package com.prakhar.reciipiie.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    recipeIdString: String
) {

    val recipeId = recipeIdString.toInt()

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .verticalScroll(rememberScrollState())
        ) {

            AsyncImage(
                modifier = Modifier.height(400.dp),
                model = "https://img.etimg.com/photo/msid-70160112,imgsize-133305/Blackicecream.jpg", // sample image
                contentDescription = "Food Image",
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 50.dp, end = 20.dp)
                .fillMaxWidth(),
            contentAlignment = androidx.compose.ui.Alignment.TopEnd
        ) {
            FloatingActionButton(contentColor = Color.Red,
                containerColor = Color.White,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(),
                onClick = { viewModel.isFavourite = !viewModel.isFavourite }) {

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
}
