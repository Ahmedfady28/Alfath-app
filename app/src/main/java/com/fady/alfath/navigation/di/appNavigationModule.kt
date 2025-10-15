package com.fady.alfath.navigation.di

import com.fady.alfath.navigation.mappers.HomeActionMapper
import com.fady.alfath.navigation.mappers.ProfileActionMapper
import com.navigation.impl.di.navigationModuleImpl
import com.navigation.impl.utils.koin.registerNavigationMapper
import org.koin.dsl.module

val appNavigationModule = module {
    includes(
        navigationMappersModule,
        navigationModuleImpl
    )
}

private val navigationMappersModule = module {
    registerNavigationMapper(HomeActionMapper)
    registerNavigationMapper(ProfileActionMapper)
}

private val featureNavigationModule = module {
    includes(

    )
}