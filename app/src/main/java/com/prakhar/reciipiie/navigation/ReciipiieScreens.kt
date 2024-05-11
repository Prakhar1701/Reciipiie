package com.prakhar.reciipiie.navigation

enum class ReciipiieScreens {
    LoginScreen, HomeScreen, DetailScreen;

    companion object {

        fun fromRoute(route: String): ReciipiieScreens = when (route.substringBefore("/")) {
            LoginScreen.name -> LoginScreen
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }

    }
}
