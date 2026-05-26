package com.example.lab6.di

import com.example.lab6.Platform
import com.example.lab6.data.AboutRepository
import com.example.lab6.data.preferences.AppPreferences
import com.example.lab6.data.preferences.Preferences
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Platform() }

    single<Settings> { Settings() }

    single<Preferences> {
        AppPreferences(
            settings = get()
        )
    }

    single {
        AboutRepository(
            platform = get(),
            preferences = get()
        )
    }

    viewModel {
        com.example.lab6.ui.AboutViewModel(get())
    }
}