package com.home.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.ui.mvi.DefaultStateHolder
import com.core.ui.mvi.IMviEventProcessor
import com.core.ui.mvi.MviStateHolder
import com.home.ui.model.HomeUiEvent
import com.home.ui.model.HomeUiState
import com.home.ui.navigation.HomeNavigationAction
import com.navigation.api.Destination
import com.navigation.api.Navigator
import com.navigation.api.model.NavigatorOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val stateHandle: SavedStateHandle,
    private val navigator: Navigator<HomeNavigationAction>,
    private val mainDispatcher: CoroutineContext = Dispatchers.Main,
    private val ioDispatcher: CoroutineContext = Dispatchers.IO,
) : ViewModel(), IMviEventProcessor<HomeUiEvent>,
    MviStateHolder<HomeUiState> by DefaultStateHolder(HomeUiState(isLoading = false)) {
    override fun onEvent(event: HomeUiEvent) =
        when (event) {
            HomeUiEvent.LoadData -> updateState { copy(isLoading = true) }
            HomeUiEvent.OnClick -> navigate(HomeNavigationAction.ToProfile)
            HomeUiEvent.OnGoBack -> navigateBack()
        }


    private fun navigate(
        target: HomeNavigationAction,
        navigatorOptions: (NavigatorOptions.() -> Unit)? = null,
    ) {
        viewModelScope.launch(mainDispatcher) {
            navigator.navigateTo(target, navigatorOptions)
        }
    }

    private fun navigate(
        target: Destination,
        navigatorOptions: (NavigatorOptions.() -> Unit)? = null,
    ) {
        viewModelScope.launch(mainDispatcher) {
            navigator.navigateTo(target, navigatorOptions)
        }
    }

    private fun navigateBack(navigatorOptions: (NavigatorOptions.() -> Unit)? = null) {
        viewModelScope.launch(mainDispatcher) {
            navigator.navigateUp(navigatorOptions)
        }
    }
}