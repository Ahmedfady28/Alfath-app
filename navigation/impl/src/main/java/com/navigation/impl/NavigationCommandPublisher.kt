package com.navigation.impl

import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.api.model.NavigationCommand
import com.navigation.api.model.NavigatorOptions
import com.navigation.impl.contract.NavigationActionMapper
import com.navigation.impl.contract.NavigationHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlin.reflect.KClass


internal class NavigationCommandPublisher(
    mappers: Set<NavigationActionMapper<NavigationAction<*>>> = setOf(),
) : NavigationHandler {

    private val _events: Channel<NavigationEvent> = Channel(capacity = Channel.BUFFERED)
    override val events: Flow<NavigationEvent> = _events.receiveAsFlow()


    private val mappersByType: Map<KClass<out NavigationAction<*>>, NavigationActionMapper<NavigationAction<*>>> =
        mappers.associateBy { it.actionType }

    override suspend fun handle(command: NavigationCommand) =
        _events.send(command.toNavigationEvent())

    private fun map(action: NavigationAction<*>): Destination =
        mappersByType[action.id.value]?.map(action)
            ?: error("No mapper found for ${action::class.simpleName}")


    private fun NavigationCommand.toNavigationEvent(): NavigationEvent = when (this) {
        is NavigationCommand.NavigateUp -> NavigationEvent.NavigateUp(
            options?.let { NavigatorOptions().apply(it) }
        )

        is NavigationCommand.NavigateTo -> NavigationEvent.NavigateTo(
            target = map(this.target),
            options = options?.let { NavigatorOptions().apply(it) }
        )

        is NavigationCommand.NavigateToDestination -> NavigationEvent.NavigateTo(
            target = target,
            options = options?.let { NavigatorOptions().apply(it) }
        )
    }

}