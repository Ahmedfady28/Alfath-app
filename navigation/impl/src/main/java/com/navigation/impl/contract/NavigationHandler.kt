package com.navigation.impl.contract

import com.navigation.api.model.NavigationCommand
import com.navigation.impl.NavigationEvent
import kotlinx.coroutines.flow.Flow

/**
 * Defines a contract for handling navigation commands within the application.
 *
 * Implementations of this interface are responsible for interpreting a [com.navigation.api.model.NavigationCommand]
 * and executing the corresponding navigation action, such as navigating to a new screen,
 * going back, or showing a dialog.
 */
interface NavigationHandler {

    val events: Flow<NavigationEvent>

    /**
     * Handles a given [com.navigation.api.model.NavigationCommand].
     *
     * This function is responsible for processing and executing navigation actions,
     * such as navigating to a new screen, going back, or handling deep links.
     * As a suspend function, it can perform asynchronous operations like fetching data
     * before navigation occurs.
     *
     * @param command The [com.navigation.api.model.NavigationCommand] to be executed.
     */
    suspend fun handle(command: NavigationCommand)
}