package com.example.lab1

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val windowIds = remember { mutableStateListOf(0) }
    var lastId = 0

    windowIds.forEach { id ->
        Window(
            onCloseRequest = {
                windowIds.remove(id)
                if (windowIds.isEmpty()) exitApplication()
            },
            title = "Світовий час - Вікно №$id",
            state = rememberWindowState()
        ) {
            MenuBar {
                Menu("File") {
                    Item(
                        "New Window",
                        shortcut = KeyShortcut(Key.N, ctrl = true),
                        onClick = {
                            lastId++
                            windowIds.add(lastId)
                        }
                    )
                    Item("Exit", onClick = { exitApplication() })
                }
            }

            App()
        }
    }
}