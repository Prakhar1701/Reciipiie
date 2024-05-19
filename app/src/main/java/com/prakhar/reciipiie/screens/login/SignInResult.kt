package com.prakhar.reciipiie.screens.login

data class SignInResult(
    val data: UserData?, val errorMessage: String?
)

data class UserData(
    val userId: String, val username: String?, val profilePictureUrl: String?
)

fun UserData.toMap(): MutableMap<String, String?> {
    return mutableMapOf(
        "user_id" to userId, "username" to username, "profile_picture_url" to profilePictureUrl
    )
}
