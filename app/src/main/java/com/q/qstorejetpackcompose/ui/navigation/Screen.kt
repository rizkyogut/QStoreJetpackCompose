package com.q.qstorejetpackcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailPhone: Screen("home/{phoneId}"){
        fun createRoute(phoneId: Long) = "home/$phoneId"
    }
}