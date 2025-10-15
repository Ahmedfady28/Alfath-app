package com.navigation.api

import com.navigation.api.model.NavigatorOptions

/**
 * A functional interface responsible for handling navigation actions within the application.
 * It provides a single method to navigate to a specified direction.
 *
 * @param D The type of the navigation command, which must extend [NavigationAction] this for enforce the type safety in other word navigation [Navigator] can only navigate to a specific [NavigationAction]s.
 */
interface Navigator<in D : NavigationAction<*>> {
    suspend fun navigateTo(to: D, builder: (NavigatorOptions.() -> Unit)? = null)
    suspend fun navigateTo(to: Destination, builder: (NavigatorOptions.() -> Unit)? = null)
    suspend fun navigateUp(builder: (NavigatorOptions.() -> Unit)? = null)
}