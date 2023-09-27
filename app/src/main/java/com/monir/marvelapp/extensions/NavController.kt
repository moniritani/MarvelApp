package com.monir.marvelapp.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

fun NavController.navigateSafe(directions: NavDirections?, navOptions: NavOptions? = null): Boolean {
    return if (directions != null && currentDestination?.getAction(directions.actionId) != null) {
        navigate(directions, navOptions)
        true
    } else false
}