package com.navigation.implementation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.navigation.api.Destination
import com.navigation.implementation.NavigationEvent
import com.navigation.implementation.contract.NavigationHandler

@Composable
internal fun NavigationHandlerEffect(
    navigationHandler: NavigationHandler,
    onNavigateTo: (Destination) -> Unit,
    onNavigateBack: () -> Unit
) {
    LaunchedEffect(navigationHandler) {
        navigationHandler.events.collect { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> onNavigateTo(event.target)
                is NavigationEvent.NavigateUp -> onNavigateBack()
            }
        }
    }
}
