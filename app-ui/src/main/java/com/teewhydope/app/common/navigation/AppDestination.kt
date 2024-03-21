package com.teewhydope.app.common.navigation

import androidx.navigation.NavController

sealed class AppDestination : UiDestination {

    data object Back : AppDestination() {
        override fun navigate(navController: NavController) {
            navController.navigateUp()
        }
    }
}