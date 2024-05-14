package com.prakhar.reciipiie.model

data class SearchRecipes(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)