package com.fady.alfath.navigation.di

import com.navigation.impl.di.navigationModuleImpl
import org.koin.dsl.module


val appModule = module {


}
val navigationModule = module {
    includes(
        navigationModuleImpl
    )
}

