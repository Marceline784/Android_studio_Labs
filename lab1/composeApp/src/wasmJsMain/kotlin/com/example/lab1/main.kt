package com.example.lab1

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

// Використовуємо сучасний спосіб імпорту для Wasm
@JsModule("@js-joda/timezone")
external val timeZoneModule: JsAny?

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // Просто звертаємось до змінної, щоб спровокувати імпорт модуля
    val init = timeZoneModule

    val root = document.getElementById("root") ?: error("Root element not found")
    ComposeViewport(root) {
        App()
    }
}