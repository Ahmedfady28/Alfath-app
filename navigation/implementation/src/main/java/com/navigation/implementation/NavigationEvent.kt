package com.navigation.implementation

import com.navigation.api.Destination
import com.navigation.api.model.NavigatorOptions

sealed interface NavigationEvent {
    data class NavigateUp(val options: NavigatorOptions? = null) : NavigationEvent
    data class NavigateTo(
        val target: Destination,
        val options: NavigatorOptions? = null,
    ) : NavigationEvent
}