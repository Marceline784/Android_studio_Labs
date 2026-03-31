package com.example.lab1.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import lab1.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource
import com.example.lab1.ui.screens.main.MainScreen
import com.example.lab1.ui.screens.buttons.ButtonsScreen
import com.example.lab1.ui.screens.checkboxes.CheckboxesScreen

// 1. Перелік екранів з посиланням на твої String Resources
enum class AppScreen(val title: StringResource) {
    Main(Res.string.main),
    Buttons(Res.string.buttons),
    Checkboxes(Res.string.checkboxes)
}

@Composable
fun AppNavigation() {
    // 2. Створюємо контролер навігації
    val navController: NavHostController = rememberNavController()

    // Отримуємо інформацію про поточний екран (для заголовків, якщо знадобиться)
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold { innerPadding ->
        // 3. Описуємо маршрути між екранами
        NavHost(
            navController = navController,
            startDestination = AppScreen.Main.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Головний екран
            composable(route = AppScreen.Main.name) {
                MainScreen(
                    onNavigateToButtons = {
                        navController.navigate(AppScreen.Buttons.name)
                    },
                    onNavigateToCheckboxes = {
                        navController.navigate(AppScreen.Checkboxes.name)
                    }
                )
            }

            // Екран з кнопками
            composable(route = AppScreen.Buttons.name) {
                ButtonsScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            // Екран з чекбоксами
            composable(route = AppScreen.Checkboxes.name) {
                CheckboxesScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}