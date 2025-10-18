package com.home.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.ui.DefaultStateHolder
import com.core.ui.IMviEventProcessor
import com.core.ui.IMviStateHolder
import com.home.ui.model.HomeUiEvent
import com.home.ui.model.HomeUiState
import com.home.ui.navigation.HomeNavigationAction
import com.navigation.api.Destination
import com.navigation.api.Navigator
import com.navigation.api.model.NavigatorOptions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val navigator: Navigator<HomeNavigationAction>,
//    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
//    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(), IMviEventProcessor<HomeUiEvent>,
    IMviStateHolder<HomeUiState> by DefaultStateHolder(HomeUiState(isLoading = true)) {
    override fun onEvent(event: HomeUiEvent) =
        when (event) {
            HomeUiEvent.LoadData -> updateState { copy(isLoading = true) }
            HomeUiEvent.OnClick -> navigate(HomeNavigationAction.ToProfile)
            HomeUiEvent.OnGoBack -> navigateBack()
        }


    fun navigate(
        target: HomeNavigationAction,
        navigatorOptions: (NavigatorOptions.() -> Unit)? = null,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            navigator.navigateTo(target, navigatorOptions)
        }
    }
    fun navigate(
        target: Destination,
        navigatorOptions: (NavigatorOptions.() -> Unit)? = null,
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            navigator.navigateTo(target, navigatorOptions)
        }
    }

    fun navigateBack(navigatorOptions: (NavigatorOptions.() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            navigator.navigateUp(navigatorOptions)
        }
    }
}