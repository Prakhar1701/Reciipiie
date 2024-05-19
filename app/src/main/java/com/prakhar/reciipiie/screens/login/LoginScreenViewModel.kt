package com.prakhar.reciipiie.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null, signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

    fun addUserToFirestoreDatabase(userData: UserData) {
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")
        val userId = userData.userId

        // Check if the user already exists
        usersCollection.whereEqualTo("user_id", userId).get().addOnSuccessListener { documents ->
            if (documents.isEmpty) {
                // User does not exist, add them to the database
                val user = userData.toMap()
                usersCollection.add(user).addOnSuccessListener { documentReference ->
                    Log.i(
                        "ADD-USER-FIRESTORE", "User added with ID: ${documentReference.id}"
                    )
                }.addOnFailureListener { e ->
                    Log.e("ADD-USER-FIRESTORE", "Error adding user: ", e)
                }
            } else {
                Log.i("ADD-USER-FIRESTORE", "User already exists in the database")
            }
        }.addOnFailureListener { e ->
            Log.e("ADD-USER-FIRESTORE", "Error checking if user exists: ", e)
        }
    }
}
