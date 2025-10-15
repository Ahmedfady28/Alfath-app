package com.navigation.impl.utils.koin

import com.navigation.api.NavigationAction
import com.navigation.impl.contract.NavigationActionMapper
import org.koin.core.module.Module

inline fun <reified A : NavigationAction<*>> Module.registerNavigationMapper(
    mapper: NavigationActionMapper<A>,
) = single<NavigationActionMapper<A>> { mapper }

