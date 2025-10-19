package com.fady.alfath.navigation.di

import com.navigation.implementation.di.navigationModuleImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext


val appModule = module {
    single(named("MainDispatcher")) { Dispatchers.Main } bind CoroutineContext::class
    single(named("IODispatcher")) { Dispatchers.IO } bind CoroutineContext::class
    single(named("DefaultDispatcher")) { Dispatchers.Default } bind CoroutineContext::class
    single(named("DefaultDispatcher")) { Dispatchers.Unconfined } bind CoroutineContext::class

}
val navigationModule = module {
    includes(
        navigationModuleImpl
    )
}

