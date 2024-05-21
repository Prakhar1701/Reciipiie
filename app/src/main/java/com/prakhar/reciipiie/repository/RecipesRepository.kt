package com.prakhar.reciipiie.repository

import com.prakhar.reciipiie.data.Resource
import com.prakhar.reciipiie.model.AutocompleteRecipeSearchItem
import com.prakhar.reciipiie.model.Recipe
import com.prakhar.reciipiie.model.Result
import com.prakhar.reciipiie.network.RecipesAPI
import javax.inject.Inject

class RecipesRepository @Inject constructor(private val api: RecipesAPI) {

    suspend fun getRandomRecipes(): Resource<List<Recipe>> {

        return try {

            Resource.Loading(data = true)

            val randomRecipeList = api.getRandomRecipes().recipes

            if (randomRecipeList.isNotEmpty()) Resource.Loading(data = false)

            Resource.Success(data = randomRecipeList)

        } catch (e: Exception) {

            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun searchRecipes(): Resource<List<Result>> {

        return try {

            Resource.Loading(data = true)

            val searchRecipeList = api.searchRecipes().results

            if (searchRecipeList.isNotEmpty()) Resource.Loading(data = false)

            Resource.Success(data = searchRecipeList)

        } catch (e: Exception) {

            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun getRecipeInformation(recipeId: Int): Resource<Recipe> {

        return try {

            Resource.Loading(data = true)

            val recipeInformation = api.getRecipeInformation(recipeId)

            Resource.Loading(data = false)

            Resource.Success(data = recipeInformation)

        } catch (e: Exception) {

            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun autocompleteRecipeSearch(query: String): Resource<List<AutocompleteRecipeSearchItem>> {

        return try {

            Resource.Loading(data = true)

            val autocompleteList = api.autocompleteRecipeSearch(query)

            if (autocompleteList.isNotEmpty()) Resource.Loading(data = false)

            Resource.Success(data = autocompleteList)

        } catch (e: Exception) {

            Resource.Error(message = e.message.toString())
        }
    }
}
