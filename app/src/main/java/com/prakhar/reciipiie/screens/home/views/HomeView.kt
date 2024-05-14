package com.prakhar.reciipiie.screens.home.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.prakhar.reciipiie.R
import com.prakhar.reciipiie.model.Recipe
import com.prakhar.reciipiie.model.Result
import com.prakhar.reciipiie.navigation.ReciipiieScreens
import com.prakhar.reciipiie.screens.home.HomeScreenViewModel

@Composable
fun HomeView(navController: NavController) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 15.dp, start = 15.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

            Text("\uD83D\uDC4B Hey <user first name>", fontSize = 20.sp)
            Text("Discover tasty and healthy receipt", fontSize = 15.sp)

            ReciipiieSearchBar()
            PopularRecipesRow(navController)
            Spacer(modifier = Modifier.height(20.dp))
            AllRecipesColumn(navController)
        }
    }
}

@Composable
fun ReciipiieSearchBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 20.dp)
            .background(Color(0xFFF2F7FD), MaterialTheme.shapes.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier.padding(start = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.search_recipe),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// Mid Composable

@Composable
private fun PopularRecipesRow(
    navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Text(text = "Popular Recipes", fontSize = 30.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(10.dp))

    val listOfRecipe = viewModel.listOfPopularRecipe

    if (viewModel.isLoadingPopularRecipe) {

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp)
        )

    } else if (!viewModel.isSuccessPopularRecipe) {

        Text(text = "Something went wrong, unable to load popular recipes\u2757")

    } else {

        LazyRow {
            items(listOfRecipe) { recipe ->
                PopularRecipeCard(recipe = recipe) {
                    navController.navigate(ReciipiieScreens.DetailScreen.name)
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }

    }
}

@Composable
fun PopularRecipeCard(recipe: Recipe, onClick: () -> Unit = {}) {

    Card(shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(),
        modifier = Modifier
            .size(170.dp)
            .clickable { onClick() }) {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = recipe.image,
                contentDescription = "Food Image",
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = recipe.title,
                    fontSize = 19.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Ready in ${recipe.readyInMinutes} min",
                    fontSize = 14.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

// Bottom Composable

@Composable
private fun AllRecipesColumn(
    navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Text(text = "All recipes", fontSize = 30.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(10.dp))

    val listOfRecipe = viewModel.listOfAllRecipe

    if (viewModel.isLoadingAllRecipe) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            CircularProgressIndicator(
                modifier = Modifier
                    .size(70.dp)
                    .padding(top = 30.dp, bottom = 20.dp)
            )
        }

    } else if (!viewModel.isSuccessAllRecipe) {

        Text(text = "Something went wrong, unable to load all recipes\u2757")

    } else {

        Column {
            listOfRecipe.forEach { result ->

                AllRecipeCard(result = result) {
                    navController.navigate(ReciipiieScreens.DetailScreen.name)
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }
}

@Composable
fun AllRecipeCard(result: Result, onClick: () -> Unit = {}) {

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
                model = result.image,
                contentDescription = "Food Image",
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = result.title,
                    fontSize = 19.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Ready in 45 min", // data not available, showing sample data
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
