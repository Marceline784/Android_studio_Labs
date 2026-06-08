package com.example.lab11.di

import com.example.lab11.data.posts.ApiService
import com.example.lab11.data.posts.ApiServiceImpl
import com.example.lab11.domain.posts.Repository
import com.example.lab11.domain.posts.RepositoryImpl
import com.example.lab11.presentation.AppViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// ✅ FIXED IMPORTS (ВАЖЛИВО)
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single {

        HttpClient {

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }

            install(Logging) {
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    single<ApiService> {
        ApiServiceImpl(get())
    }

    single<Repository> {
        RepositoryImpl(get())
    }

    factory {
        AppViewModel(get())
    }
}