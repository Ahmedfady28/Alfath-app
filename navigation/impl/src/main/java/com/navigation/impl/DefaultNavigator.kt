package com.navigation.impl

import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.api.Navigator
import com.navigation.api.model.NavigationCommand
import com.navigation.api.model.NavigatorOptions
import com.navigation.impl.contract.NavigationHandler

internal class DefaultNavigator<in A : NavigationAction<*>>(
    private val handler: NavigationHandler,
) : Navigator<A> {

    override suspend fun navigateTo(to: A, builder: (NavigatorOptions.() -> Unit)?) =
        handler.handle(NavigationCommand.NavigateTo(to, builder))


    override suspend fun navigateTo(
        to: Destination,
        builder: (NavigatorOptions.() -> Unit)?,
    ) = handler.handle(NavigationCommand.NavigateToDestination(to, builder))

    override suspend fun navigateUp(builder: (NavigatorOptions.() -> Unit)?) =
        handler.handle(NavigationCommand.NavigateUp(builder))
}