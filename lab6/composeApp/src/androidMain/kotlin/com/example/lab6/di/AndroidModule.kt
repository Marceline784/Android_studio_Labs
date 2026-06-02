package com.example.lab6.di

import com.example.lab6.Organise
import com.example.lab6.data.common.db.DatabaseDriverFactory
import com.example.lab6.data.common.db.DbDataSource
import com.example.lab6.data.common.db.LocalDataSource
import com.example.lab6.data.reminders.RemindersRepository
import com.example.lab6.ui.AboutViewModel
import com.example.lab6.ui.Lab7ViewModel
import com.example.lab6.ui.reminders.ReminderViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    single { DatabaseDriverFactory(androidContext()) }
    single<Organise> {
        val factory = get<DatabaseDriverFactory>()
        Organise(factory.create())
    }
    single<LocalDataSource> { DbDataSource(get()) }
    single { RemindersRepository(get()) }

    viewModel { AboutViewModel(get()) }
    viewModel { Lab7ViewModel() }
    viewModel { ReminderViewModel(get()) }
}