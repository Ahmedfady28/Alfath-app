package com.navigation.impl.di

import com.navigation.api.NavigationAction
import com.navigation.api.Navigator
import com.navigation.impl.DefaultNavigator
import com.navigation.impl.NavigationCommandPublisher
import com.navigation.impl.contract.NavigationHandler
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val navigationModuleImpl = module {
    singleOf(::NavigationCommandPublisher) bind NavigationHandler::class
    single<Navigator<NavigationAction<*>>> { DefaultNavigator(get()) }
}