package com.profile.ui.di

import com.core.ui.ScreenFeatureUi
import com.profile.ui.navigation.ProfileFeatureUi
import com.profile.ui.viewmodel.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ProfileUiModule = module {
    viewModelOf(::ProfileViewModel)
    single<ScreenFeatureUi> { ProfileFeatureUi }
}