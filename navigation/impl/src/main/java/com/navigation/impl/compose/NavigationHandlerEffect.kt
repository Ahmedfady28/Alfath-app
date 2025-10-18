package com.navigation.impl.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.navigation.api.Destination
import com.navigation.impl.NavigationEvent
import com.navigation.impl.contract.NavigationHandler

@Composable
internal fun NavigationHandlerEffect(
    navigationHandler: NavigationHandler,
    onNavigateTo: (Destination) -> Unit,
    onNavigateBack: () -> Unit,
    content: @Composable () -> Unit
) {
    LaunchedEffect(navigationHandler) {
        navigationHandler.events.collect { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> onNavigateTo(event.target)
                is NavigationEvent.NavigateUp -> onNavigateBack()
            }
        }
    }
    content()
}
