package com.example.lab1

import androidx.compose.runtime.Composable
import com.example.lab1.ui.theme.AppTheme
import com.example.lab1.ui.screens.AppNavigation

@Composable

fun App() {
    AppTheme {
        // App тепер лише запускає навігацію
        AppNavigation()
    }
}