package com.teewhydope.app.common.navigation

import androidx.navigation.NavController

interface UiDestination {
    fun navigate(navController: NavController)
}