package com.navigation.implementation.di

import com.home.ui.di.HomeUiModule
import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.api.Navigator
import com.navigation.implementation.DefaultNavigator
import com.navigation.implementation.NavigationCommandPublisher
import com.navigation.implementation.contract.NavigationActionMapper
import com.navigation.implementation.contract.NavigationHandler
import com.navigation.implementation.mappers.HomeActionMapper
import com.navigation.implementation.mappers.ProfileActionMapper
import com.profile.ui.di.ProfileUiModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val navigationModuleImpl = module {
    singleOf(::DefaultNavigator) bind Navigator::class

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

private inline fun <reified D : Destination, reified A : NavigationAction<D>> Module.registerNavigationMapper(
    mapper: NavigationActionMapper<A>,
) = single<NavigationActionMapper<A>> { mapper }