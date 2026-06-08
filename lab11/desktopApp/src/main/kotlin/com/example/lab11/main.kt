package com.example.lab11

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.lab11.di.initKoin
import com.example.lab11.presentation.App

fun main() {

    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Lab11"
        ) {
            App()
        }
    }
}