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
import com.example.lab1.ui.screens.buttons.ButtonsScreen
import com.example.lab1.ui.screens.checkboxes.CheckboxesScreen
import com.example.lab1.ui.screens.chips.ChipsScreen
import com.example.lab1.ui.screens.datepicker.DatePickerScreen
import com.example.lab1.ui.screens.dialogs.DialogScreen
import com.example.lab1.ui.screens.divider.DividerScreen
import com.example.lab1.ui.screens.progress.ProgressScreen
import com.example.lab1.ui.screens.radio.RadioScreen
import com.example.lab1.ui.screens.switches.SwitchScreen
import com.example.lab1.ui.screens.timepicker.TimePickerScreen

enum class AppScreen {
    Main, Buttons, Checkboxes, Chips, DatePicker, Dialogs, Dividers, Progress, Radio, Switches, TimePicker, Lab4
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
                    onButtonsClicked = { navController.navigate(AppScreen.Buttons.name) },
                    onCheckboxesClicked = { navController.navigate(AppScreen.Checkboxes.name) },
                    onChipsClicked = { navController.navigate(AppScreen.Chips.name) },
                    onDatePickerClicked = { navController.navigate(AppScreen.DatePicker.name) },
                    onDialogsClicked = { navController.navigate(AppScreen.Dialogs.name) },
                    onDividersClicked = { navController.navigate(AppScreen.Dividers.name) },
                    onProgressClicked = { navController.navigate(AppScreen.Progress.name) },
                    onRadioClicked = { navController.navigate(AppScreen.Radio.name) },
                    onSwitchesClicked = { navController.navigate(AppScreen.Switches.name) },
                    onTimePickerClicked = { navController.navigate(AppScreen.TimePicker.name) },
                    onLab4Clicked = { navController.navigate(AppScreen.Lab4.name) }
                )
            }

            composable(route = AppScreen.Buttons.name) {
                ButtonsScreen(
                    onBack = { navController.popBackStack() },
                    onShowMessage = { text ->
                        scope.launch { snackbarHostState.showSnackbar(message = text) }
                    }
                )
            }

            composable(route = AppScreen.Checkboxes.name) {
                CheckboxesScreen(onBack = { navController.popBackStack() })
            }

            composable(route = AppScreen.Chips.name) {
                ChipsScreen(onBack = { navController.popBackStack() })
            }

            composable(route = AppScreen.DatePicker.name) {
                DatePickerScreen(onBack = { navController.popBackStack() })
            }

            composable(route = AppScreen.Dialogs.name) {
                DialogScreen(onBack = { navController.popBackStack() })
            }

            composable(route = AppScreen.Dividers.name) {
                DividerScreen(onBack = { navController.popBackStack() })
            }

            composable(route = AppScreen.Progress.name) {
                ProgressScreen(onBack = { navController.popBackStack() })
            }

            composable(route = AppScreen.Radio.name) {
                RadioScreen(onBack = { navController.popBackStack() })
            }

            composable(route = AppScreen.Switches.name) {
                SwitchScreen(onBack = { navController.popBackStack() })
            }

            composable(route = AppScreen.TimePicker.name) {
                TimePickerScreen(onBack = { navController.popBackStack() })
            }
            composable(route = AppScreen.Lab4.name) {
                com.example.lab1.ui.screens.lab4.Lab4MainScreen(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}