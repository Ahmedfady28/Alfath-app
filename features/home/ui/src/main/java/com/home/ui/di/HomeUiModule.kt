package com.home.ui.di

import com.core.ui.ScreenFeatureUi
import com.home.ui.navigation.HomeFeatureUi
import com.home.ui.viewmodel.HomeViewModel
import com.navigation.api.NavigationAction
import com.navigation.api.Navigator
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val HomeUiModule = module {
//    viewModelOf(::HomeViewModel)

    viewModel {
        HomeViewModel(
            navigator = get<Navigator<NavigationAction<*>>>()
        )
    }
    single<ScreenFeatureUi> { HomeFeatureUi }
}