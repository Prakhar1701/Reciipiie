package com.prakhar.reciipiie.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prakhar.reciipiie.data.Resource
import com.prakhar.reciipiie.model.Recipe
import com.prakhar.reciipiie.model.Result
import com.prakhar.reciipiie.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val recipesRepository: RecipesRepository) :
    ViewModel() {

    var listOfPopularRecipe: List<Recipe> by mutableStateOf(listOf())

    var isLoadingPopularRecipe: Boolean by mutableStateOf(true)

    var isSuccessPopularRecipe: Boolean by mutableStateOf(false)

    var listOfAllRecipe: List<Result> by mutableStateOf(listOf())

    var isLoadingAllRecipe: Boolean by mutableStateOf(true)

    var isSuccessAllRecipe: Boolean by mutableStateOf(false)

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        getPopularRecipes()
        getAllRecipes()
    }

    private fun getPopularRecipes() {

        viewModelScope.launch {

            try {
                when (val recipes = recipesRepository.getRandomRecipes()) {

                    is Resource.Success -> {

                        listOfPopularRecipe = recipes.data!!

                        if (listOfPopularRecipe.isNotEmpty()) {
                            isLoadingPopularRecipe = false
                            isSuccessPopularRecipe = true
                        }

                    }

                    is Resource.Error -> {

                        isLoadingPopularRecipe = false
                        isSuccessPopularRecipe = false

                        Log.d("API", "GET-RANDOM-RECIPES RESOURCE-ERROR: ${recipes.message}")
                    }

                    else -> {
                        isLoadingPopularRecipe = false
                    }
                }
            } catch (exception: Exception) {

                isLoadingPopularRecipe = false

                Log.d("API", "GET-RANDOM-RECIPES EXCEPTION: ${exception.message}")
            }
        }
    }

    private fun getAllRecipes() {

        viewModelScope.launch {

            try {
                when (val result = recipesRepository.searchRecipes()) {

                    is Resource.Success -> {

                        listOfAllRecipe = result.data!!

                        if (listOfAllRecipe.isNotEmpty()) {
                            isLoadingAllRecipe = false
                            isSuccessAllRecipe = true
                        }

                    }

                    is Resource.Error -> {

                        isLoadingAllRecipe = false
                        isSuccessAllRecipe = false

                        Log.d("API", "GET-ALL-RECIPES RESOURCE-ERROR: ${result.message}")
                    }

                    else -> {
                        isLoadingAllRecipe = false
                    }
                }
            } catch (exception: Exception) {

                isLoadingAllRecipe = false

                Log.d("API", "GET-ALL-RECIPES EXCEPTION: ${exception.message}")
            }
        }
    }
}
