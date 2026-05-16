package com.example.lab6.di

import com.example.lab6.Platform
import com.example.lab6.data.AboutRepository
import com.example.lab6.ui.AboutViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Platform()
    }

    single {
        AboutRepository(get())
    }

    viewModel {
        AboutViewModel(get())
    }
}