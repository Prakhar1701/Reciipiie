package com.prakhar.reciipiie.network

import com.prakhar.reciipiie.BuildConfig
import com.prakhar.reciipiie.model.AutocompleteRecipeSearch
import com.prakhar.reciipiie.model.RandomRecipes
import com.prakhar.reciipiie.model.Recipe
import com.prakhar.reciipiie.model.SearchRecipes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface RecipesAPI {

    @GET("random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int = 5, @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): RandomRecipes

    @GET("complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): SearchRecipes

    @GET("{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int, @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): Recipe

    @GET("autocomplete")
    suspend fun autocompleteRecipeSearch(
        @Query("query") query: String,
        @Query("number") number: Int = 10,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): AutocompleteRecipeSearch
}
