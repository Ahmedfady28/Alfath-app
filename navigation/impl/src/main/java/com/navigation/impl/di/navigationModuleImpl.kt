package com.navigation.impl.di

import com.home.ui.di.HomeUiModule
import com.navigation.api.NavigationAction
import com.navigation.api.Navigator
import com.navigation.impl.DefaultNavigator
import com.navigation.impl.NavigationCommandPublisher
import com.navigation.impl.contract.NavigationActionMapper
import com.navigation.impl.contract.NavigationHandler
import com.navigation.impl.mappers.HomeActionMapper
import com.navigation.impl.mappers.ProfileActionMapper
import com.navigation.impl.utils.koin.registerNavigationMapper
import com.navigation.impl.utils.koin.registerNavigationMappers
import com.profile.ui.di.ProfileUiModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val navigationModuleImpl = module {
    singleOf(::DefaultNavigator) bind Navigator::class

    registerNavigationMappers(
        HomeActionMapper,
        ProfileActionMapper
    )
    registerNavigationMapper(HomeActionMapper)
    registerNavigationMapper(ProfileActionMapper)

    single<Set<NavigationActionMapper<NavigationAction<*>>>> {
        setOf(
            HomeActionMapper,
            ProfileActionMapper
        )
    }

    singleOf(::NavigationCommandPublisher) bind NavigationHandler::class

    includes(
        HomeUiModule,
        ProfileUiModule,
    )

}