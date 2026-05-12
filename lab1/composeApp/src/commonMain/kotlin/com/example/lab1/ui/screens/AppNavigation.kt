package com.example.lab1.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import kotlinx.coroutines.launch
import lab1.composeapp.generated.resources.*
import com.example.lab1.ui.screens.main.MainScreen


enum class AppScreen {
    Main, Lab4
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        // ДОДАЄМО .padding(innerPadding), щоб контент не перекривався
        NavHost(
            navController = navController,
            startDestination = AppScreen.Main.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = AppScreen.Main.name) {
                MainScreen(
                    onLab4Clicked = { navController.navigate(AppScreen.Lab4.name) }
                )
            }

            composable(route = AppScreen.Lab4.name) {
                com.example.lab1.ui.screens.lab4.Lab4MainScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}