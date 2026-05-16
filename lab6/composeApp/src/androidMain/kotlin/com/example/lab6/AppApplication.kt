package com.example.lab6

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import com.example.lab6.di.initKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {

            androidContext(this@AppApplication)

            androidLogger()
        }
    }
}