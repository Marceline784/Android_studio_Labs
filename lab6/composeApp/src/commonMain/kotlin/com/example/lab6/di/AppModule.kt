package com.example.lab6.di

import com.example.lab6.Platform
import com.example.lab6.data.AboutRepository
import com.example.lab6.data.preferences.AppPreferences
import com.example.lab6.data.preferences.Preferences
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val appModule = module {
    single { Platform() }
    single { Settings() }
    single<Preferences> { AppPreferences(get()) }
    single { AboutRepository(get(), get()) }
}