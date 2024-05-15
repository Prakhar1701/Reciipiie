package com.prakhar.reciipiie.screens.detail

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
class DetailScreenViewModel @Inject constructor(private val recipesRepository: RecipesRepository) :
    ViewModel() {

    var isFavourite: Boolean by mutableStateOf(false)

    var recipeInformation: Recipe? by mutableStateOf(null)

    var isLoadingRecipeInformation: Boolean by mutableStateOf(true)

    var isSuccessRecipeInformation: Boolean by mutableStateOf(false)

    fun getRecipeInformation(recipeId: Int) {

        viewModelScope.launch {

            try {
                when (val recipeInformation = recipesRepository.getRecipeInformation(recipeId)) {

                    is Resource.Success -> {

                        this@DetailScreenViewModel.recipeInformation = recipeInformation.data!!

                        isLoadingRecipeInformation = false
                        isSuccessRecipeInformation = true
                    }

                    is Resource.Error -> {

                        isLoadingRecipeInformation = false
                        isSuccessRecipeInformation = false

                        Log.d(
                            "API",
                            "GET-RECIPE-INFORMATION RESOURCE-ERROR: ${recipeInformation.message}"
                        )
                    }

                    else -> {
                        isLoadingRecipeInformation = false
                    }
                }
            } catch (exception: Exception) {

                isLoadingRecipeInformation = false

                Log.d("API", "GET-RECIPE-INFORMATION EXCEPTION: ${exception.message}")
            }
        }
    }
}
