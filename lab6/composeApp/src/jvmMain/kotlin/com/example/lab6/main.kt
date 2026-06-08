package com.example.lab6

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.lab6.di.appModule
import com.example.lab6.di.jvmModule // Імпортуємо наш новий модуль
import com.example.lab6.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.stopKoin

fun main() = application {

    Napier.base(DebugAntilog())

    initKoin {
        modules(appModule, jvmModule)
    }

    Window(
        onCloseRequest = {
            stopKoin()
            exitApplication()
        },
        title = "Lab6",
    ) {
        App()
    }
}