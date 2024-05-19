package com.prakhar.reciipiie.model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class Recipe(

    @Exclude var aggregateLikes: Int? = null,
    @Exclude var analyzedInstructions: List<AnalyzedInstruction>? = null,
    @Exclude var cheap: Boolean? = null,
    @Exclude var cookingMinutes: Int? = null,
    @Exclude var creditsText: String? = null,
    @Exclude var cuisines: List<String>? = null,
    @Exclude var dairyFree: Boolean? = null,
    @Exclude var diets: List<String>? = null,
    @Exclude var dishTypes: List<String>? = null,
    @Exclude var extendedIngredients: List<ExtendedIngredient>? = null,
    @Exclude var gaps: String? = null,
    @Exclude var glutenFree: Boolean? = null,
    @Exclude var healthScore: Int? = null,

    @get:PropertyName("recipe_id") @set:PropertyName("recipe_id") var id: Int? = null,

    @get:PropertyName("recipe_image_url") @set:PropertyName("recipe_image_url") var image: String? = null,

    @Exclude var imageType: String? = null,
    @Exclude var instructions: String? = null,
    @Exclude var license: String? = null,
    @Exclude var lowFodmap: Boolean? = null,
    @Exclude var occasions: List<Any>? = null,
    @Exclude var originalId: Any? = null,
    @Exclude var preparationMinutes: Int? = null,
    @Exclude var pricePerServing: Double? = null,

    @get:PropertyName("recipe_ready_in_minutes") @set:PropertyName("recipe_ready_in_minutes") var readyInMinutes: Int? = null,

    @Exclude var servings: Int? = null,
    @Exclude var sourceName: String? = null,
    @Exclude var sourceUrl: String? = null,
    @Exclude var spoonacularScore: Double? = null,
    @Exclude var spoonacularSourceUrl: String? = null,
    @Exclude var summary: String? = null,
    @Exclude var sustainable: Boolean? = null,

    @get:PropertyName("recipe_title") @set:PropertyName("recipe_title") var title: String? = null,

    @Exclude var vegan: Boolean? = null,
    @Exclude var vegetarian: Boolean? = null,
    @Exclude var veryHealthy: Boolean? = null,
    @Exclude var veryPopular: Boolean? = null,
    @Exclude var weightWatcherSmartPoints: Int? = null,

    )