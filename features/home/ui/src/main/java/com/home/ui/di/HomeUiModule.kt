package com.home.ui.di

import com.home.ui.viewmodel.HomeViewModel
import com.navigation.api.NavigationAction
import com.navigation.api.Navigator
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val HomeUiModule = module {
    viewModel {
        HomeViewModel(
            navigator = get<Navigator<NavigationAction<*>>>(),
            mainDispatcher = get(named("MainDispatcher")),
            ioDispatcher = get(named("IODispatcher")),
            stateHandle = get()
        )
    }
}