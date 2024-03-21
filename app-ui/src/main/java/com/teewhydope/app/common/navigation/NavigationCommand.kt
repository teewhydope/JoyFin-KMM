package com.teewhydope.app.common.navigation

import android.util.Log
import androidx.navigation.NavAction
import androidx.navigation.NavController
import androidx.navigation.NavDirections

/**
 * Sealed class to restrict the type of commands the viewModels can send to BaseFragments
 */
sealed class NavigationCommand {
    data class To(val direction: NavDirections) : NavigationCommand()
    object Back : NavigationCommand()
}

/**
 * This method avoids simultaneous attempts to navigate while the navController is processing
 * current navigation
 */
fun NavController.navigateSafe(navDirections: NavDirections) {
    val action: NavAction? = currentDestination?.getAction(navDirections.actionId)
        ?: graph.getAction(navDirections.actionId)
    if (action != null && currentDestination?.id != action.destinationId) {
        val destinationTo = graph.findNode(action.destinationId)
        destinationTo?.let {
            Log.d(
                NavigationCommand::class.java.simpleName + "_TAG",
                "navigateSafe: navigateTo -> ${destinationTo?.label} id:${action.destinationId}\""
            )
        } ?: logIllegalAttempt(action.destinationId.toString())
    } else {
        logIllegalAttempt(action?.destinationId.toString())
    }
    this.navigate(navDirections)
}

private fun logIllegalAttempt(destinationId: String) {
    Log.d(
        NavigationCommand::class.java.simpleName + "_TAG",
        "navigateSafe: Illegal navigation attempt, node with id $destinationId not found"
    )
}