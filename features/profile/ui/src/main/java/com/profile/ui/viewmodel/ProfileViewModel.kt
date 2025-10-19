package com.profile.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.ui.mvi.DefaultStateHolder
import com.core.ui.mvi.IMviEventProcessor
import com.core.ui.mvi.MviStateHolder
import com.navigation.api.Navigator
import com.navigation.api.model.NavigatorOptions
import com.profile.ui.model.ProfileUiEvent
import com.profile.ui.model.ProfileUiState
import com.profile.ui.navigation.ProfileNavigationAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(
    private val navigator: Navigator<ProfileNavigationAction>,
    private val stateHandle: SavedStateHandle,
    private val mainDispatcher: CoroutineContext = Dispatchers.Main,
    private val ioDispatcher: CoroutineContext = Dispatchers.IO,
) : ViewModel(), IMviEventProcessor<ProfileUiEvent>,
    MviStateHolder<ProfileUiState> by DefaultStateHolder(ProfileUiState()) {


    override fun onEvent(event: ProfileUiEvent) {
        when (event) {
            ProfileUiEvent.GoBack -> navigateUp()
        }
    }

    private fun navigate(action: ProfileNavigationAction, options: (NavigatorOptions.() -> Unit)?) =
        viewModelScope.launch(mainDispatcher) {
            navigator.navigateTo(action, options)
        }

    private fun navigateUp(options: (NavigatorOptions.() -> Unit)? = null) = viewModelScope
        .launch(mainDispatcher) {
            navigator.navigateUp(options)
        }
}