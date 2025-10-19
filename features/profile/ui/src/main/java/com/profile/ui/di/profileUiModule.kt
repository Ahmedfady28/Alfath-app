package com.profile.ui.di

import com.navigation.api.NavigationAction
import com.navigation.api.Navigator
import com.profile.ui.viewmodel.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val ProfileUiModule = module {
    viewModel {
        ProfileViewModel(
            navigator = get<Navigator<NavigationAction<*>>>(),
            mainDispatcher = get(named("MainDispatcher")),
            ioDispatcher = get(named("IODispatcher")),
            stateHandle = get()
        )
    }
}