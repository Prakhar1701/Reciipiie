package com.prakhar.reciipiie.network

import com.prakhar.reciipiie.BuildConfig
import com.prakhar.reciipiie.model.Recipes
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface RecipesAPI {

    @GET("random")
    suspend fun getRandomRecipes(@Query("apiKey") apiKey: String = BuildConfig.API_KEY): Recipes
}
