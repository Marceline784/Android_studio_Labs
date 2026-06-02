package com.example.lab6

import android.app.Application
import com.example.lab6.di.androidModule
import com.example.lab6.di.appModule
import com.example.lab6.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AppApplication)
            androidLogger()
            modules(appModule, androidModule)
        }
    }
}