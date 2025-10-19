package com.navigation.implementation

import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.api.Navigator
import com.navigation.api.model.NavigationCommand
import com.navigation.api.model.NavigatorOptions
import com.navigation.implementation.contract.NavigationHandler

internal class DefaultNavigator(
    private val handler: NavigationHandler,
) : Navigator<NavigationAction<*>> {

    override suspend fun navigateTo(
        to: NavigationAction<*>,
        builder: (NavigatorOptions.() -> Unit)?,
    ) = handler.handle(NavigationCommand.NavigateTo(to, builder))


    override suspend fun navigateTo(
        to: Destination,
        builder: (NavigatorOptions.() -> Unit)?,
    ) = handler.handle(NavigationCommand.NavigateToDestination(to, builder))

    override suspend fun navigateUp(builder: (NavigatorOptions.() -> Unit)?) =
        handler.handle(NavigationCommand.NavigateUp(builder))
}