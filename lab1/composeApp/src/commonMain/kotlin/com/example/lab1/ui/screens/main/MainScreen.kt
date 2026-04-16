package com.example.lab1.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*
import com.example.lab1.TimeZoneHelperImpl
import co.touchlab.kermit.Logger

@Composable
fun MainScreen(
    onButtonsClicked: () -> Unit,
    onCheckboxesClicked: () -> Unit,
    onChipsClicked: () -> Unit,
    onDatePickerClicked: () -> Unit,
    onDialogsClicked: () -> Unit,
    onDividersClicked: () -> Unit,
    onProgressClicked: () -> Unit,
    onRadioClicked: () -> Unit,
    onSwitchesClicked: () -> Unit,
    onTimePickerClicked: () -> Unit,
    onLab4Clicked: () -> Unit
) {
    var showContent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // ТВОЯ КНОПКА ЧАСУ
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                showContent = !showContent
                Logger.i { "Logger test: стан часу змінено" }
            }
        ) {
            Text(if (showContent) "Сховати час" else "Показати час")
        }

        AnimatedVisibility(showContent) {
            val currentTime = remember { TimeZoneHelperImpl().currentTime() }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Поточний час: $currentTime",
                    style = MaterialTheme.typography.displayMedium.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // КНОПКИ НАВІГАЦІЇ
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onButtonsClicked
        ) {
            Text(stringResource(Res.string.buttons))
        }

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onCheckboxesClicked
        ) {
            Text(stringResource(Res.string.checkboxes))
        }

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onChipsClicked
        ) {
            Text("Chips")
        }

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onDatePickerClicked // Викликаємо нову дію
        ) {
            Text("Date Picker")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onDialogsClicked
        ) {
            Text("Dialogs")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onDividersClicked
        ) {
            Text("Dividers")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onProgressClicked
        ) {
            Text("Progress Indicators")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onRadioClicked
        ) {
            Text("Radio Buttons")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onSwitchesClicked
        ) {
            Text("Switches")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onTimePickerClicked
        ) {
            Text("Time Picker")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onLab4Clicked
        ) {
            Text("Лабораторна №4 (Time Zones)")
        }
    }
}