package com.example.lab6

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun main() = application {
    // Ініціалізація логера для Desktop
    Napier.base(DebugAntilog())

    Window(
        onCloseRequest = ::exitApplication,
        title = "Lab6",
    ) {
        App()
    }
}