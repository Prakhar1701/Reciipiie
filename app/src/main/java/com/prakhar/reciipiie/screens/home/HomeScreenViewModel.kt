package com.prakhar.reciipiie.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prakhar.reciipiie.data.Resource
import com.prakhar.reciipiie.model.Recipe
import com.prakhar.reciipiie.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val recipesRepository: RecipesRepository) :
    ViewModel() {

    var listOfPopularRecipe: List<Recipe> by mutableStateOf(listOf())

    var isLoading: Boolean by mutableStateOf(true)

    var isSuccess: Boolean by mutableStateOf(false)

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        getPopularRecipes()
    }

    private fun getPopularRecipes() {

        viewModelScope.launch {
            try {
                when (val recipes = recipesRepository.getRandomRecipes()) {
                    is Resource.Success -> handleSuccess(recipes.data)
                    is Resource.Error -> handleError(recipes.message)
                    else -> isLoading = false
                }
            } catch (exception: Exception) {
                handleError(exception.message)
            } finally {
                isLoading = false
            }
        }
    }

    private fun handleSuccess(recipes: List<Recipe>?) {
        if (!recipes.isNullOrEmpty()) {
            listOfPopularRecipe = recipes
            isSuccess = true
        }
    }

    private fun handleError(errorMessage: String?) {
        isSuccess = false
        errorMessage?.let { Log.d(LOG_TAG_API, it) }
    }

    companion object {
        private const val LOG_TAG_API = "API"
    }
}
