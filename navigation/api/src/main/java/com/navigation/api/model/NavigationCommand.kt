package com.navigation.api.model

import com.navigation.api.Destination
import com.navigation.api.NavigationAction

sealed interface NavigationCommand {
    data class NavigateTo(
        val target: NavigationAction<*>,
        val options: (NavigatorOptions.()-> Unit)? = null
    ) : NavigationCommand
    data class NavigateUp(val options: (NavigatorOptions.()-> Unit)? = null) : NavigationCommand
    data class NavigateToDestination(
        val target: Destination,
        val options: (NavigatorOptions.()-> Unit)? = null
    ) : NavigationCommand
}