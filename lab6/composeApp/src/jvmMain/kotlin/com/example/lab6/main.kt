package com.example.lab6

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.lab6.di.initKoin
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main() = application {

    Napier.base(DebugAntilog())

    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Lab6",
    ) {

        App()
    }
}