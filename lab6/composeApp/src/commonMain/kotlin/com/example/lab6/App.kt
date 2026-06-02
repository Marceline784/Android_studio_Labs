package com.example.lab6

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.lab6.ui.*
import com.example.lab6.ui.theme.MyCustomTheme
import com.example.lab6.ui.reminders.RemindersScreen

// У App.kt
@Composable
fun App() {
    var screen by remember { mutableStateOf("menu") }

    MyCustomTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when (screen) {
                "menu" -> MenuScreen(
                    onLab6 = { screen = "lab6" },
                    onLab7 = { screen = "lab7" },
                    onLab8 = { screen = "lab8" },
                    onLab9 = { screen = "lab9" },
                    onLab10 = { screen = "lab10" }
                )
                "lab6" -> Lab6Screen(onBack = { screen = "menu" })
                "lab7" -> Lab7Screen(onBack = { screen = "menu" })
                "lab8" -> AboutScreen(onBack = { screen = "menu" })
                "lab9" -> Lab9Screen(onBack = { screen = "menu" })
                "lab10" -> RemindersScreen(onBack = { screen = "menu" })
            }
        }
    }
}