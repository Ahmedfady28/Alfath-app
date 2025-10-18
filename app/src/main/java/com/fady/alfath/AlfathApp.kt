package com.fady.alfath

import android.app.Application
import com.fady.alfath.navigation.di.appModule
import com.fady.alfath.navigation.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AlfathApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AlfathApp)
            androidLogger(Level.DEBUG)
            modules(
                appModule,
                navigationModule
            )
        }
    }
}