package com.prakhar.reciipiie.repository

import com.prakhar.reciipiie.data.Resource
import com.prakhar.reciipiie.model.Recipe
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
}
