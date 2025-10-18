package com.navigation.impl.utils.koin

import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.impl.contract.NavigationActionMapper
import org.koin.core.module.Module
import org.koin.core.qualifier.TypeQualifier

inline fun <reified A : NavigationAction<*>> Module.registerNavigationMappers(
    vararg mappers: NavigationActionMapper<out A>,
) = mappers.forEach { mapper ->
    val qualifier = TypeQualifier(A::class)
    single<NavigationActionMapper<A>>(qualifier) { mapper }
}

inline fun <reified D : Destination, reified A : NavigationAction<D>> Module.registerNavigationMapper(
    mapper: NavigationActionMapper<A>,
) = single<NavigationActionMapper<A>> { mapper }
