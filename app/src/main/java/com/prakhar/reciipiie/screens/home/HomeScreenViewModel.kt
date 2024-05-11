package com.prakhar.reciipiie.screens.home

import androidx.lifecycle.ViewModel
import com.prakhar.reciipiie.repository.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val recipesRepository: RecipesRepository) :
    ViewModel() {}
