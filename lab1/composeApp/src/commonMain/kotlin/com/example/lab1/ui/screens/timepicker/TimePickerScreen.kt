@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab1.ui.screens.timepicker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*
import java.util.Calendar

@Composable
fun TimePickerScreen(onBack: () -> Unit) {
    var selectedTimeText by remember { mutableStateOf("Час не вибрано") }
    var showDial by remember { mutableStateOf(false) }
    var showInput by remember { mutableStateOf(false) }

    val currentTime = Calendar.getInstance()
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Time Picker Components", style = MaterialTheme.typography.headlineLarge)

        Text(
            text = "Результат: $selectedTimeText",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        // 1. Кнопка для Dial Picker
        Button(onClick = { showDial = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Show Dial Time Picker")
        }

        // 2. Кнопка для Input Picker
        Button(onClick = { showInput = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Show Input Time Picker")
        }

        // --- ЛОГІКА ДІАЛОГІВ ---

        if (showDial || showInput) {
            AlertDialog(
                onDismissRequest = { showDial = false; showInput = false },
                confirmButton = {
                    TextButton(onClick = {
                        selectedTimeText = "${timePickerState.hour}:${timePickerState.minute.toString().padStart(2, '0')}"
                        showDial = false
                        showInput = false
                    }) { Text("Confirm") }
                },
                dismissButton = {
                    TextButton(onClick = { showDial = false; showInput = false }) { Text("Dismiss") }
                },
                text = {
                    if (showDial) {
                        TimePicker(state = timePickerState)
                    } else {
                        TimeInput(state = timePickerState)
                    }
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // КНОПКА НАЗАД
        Button(modifier = Modifier.fillMaxWidth(), onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}