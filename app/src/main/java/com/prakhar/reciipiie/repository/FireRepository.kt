package com.prakhar.reciipiie.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.prakhar.reciipiie.data.DataOrException
import com.prakhar.reciipiie.model.Recipe
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(private val favourites: CollectionReference) {

    suspend fun getUserFavourites(userId: String): DataOrException<List<Recipe>, Boolean, Exception> {

        val dataOrException = DataOrException<List<Recipe>, Boolean, Exception>()

        try {

            dataOrException.loading = true

            dataOrException.data = favourites.whereEqualTo("user_id", userId).get()
                .await().documents.map { documentSnapshot ->
                    documentSnapshot.toObject(Recipe::class.java)!!
                }

            if (!dataOrException.data.isNullOrEmpty()) {
                dataOrException.loading = false
            }

        } catch (exception: FirebaseFirestoreException) {
            dataOrException.e = exception
        }

        return dataOrException
    }


    suspend fun saveUserFavourite(userId: String, recipe: Recipe) {

        val firestoreRecipe = hashMapOf(
            "user_id" to userId,
            "recipe_id" to recipe.id,
            "recipe_title" to recipe.title,
            "recipe_ready_in_minutes" to recipe.readyInMinutes,
            "recipe_image_url" to recipe.image
        )

        favourites.add(firestoreRecipe).addOnSuccessListener { documentReference ->
            Log.d("ADD-RECIPE-FIRESTORE", "DocumentSnapshot added with ID: ${documentReference.id}")
        }.addOnFailureListener { e ->
            Log.w("ADD-RECIPE-FIRESTORE", "Error adding document", e)
        }

    }

    suspend fun deleteUserFavourite(userId: String, recipe: Recipe) {

        val favouritesCollection =
            favourites.whereEqualTo("user_id", userId).whereEqualTo("recipe_id", recipe.id)

        favouritesCollection.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents) {
                favourites.document(document.id).delete().addOnSuccessListener {
                    Log.d("DELETE-RECIPE-FIRESTORE", "DocumentSnapshot successfully deleted!")
                }.addOnFailureListener { e ->
                    Log.w("DELETE-RECIPE-FIRESTORE", "Error deleting document", e)
                }
            }
        }.addOnFailureListener { e ->
            Log.w("DELETE-RECIPE-FIRESTORE", "Error finding document to delete", e)
        }
    }
}
