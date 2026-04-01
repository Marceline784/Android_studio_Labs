package com.example.lab1.ui.screens.checkboxes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*

@Composable
fun CheckboxesScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(Res.string.checkboxes),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // --- 1. CheckboxMinimalExample ---
        Text("Minimal example", style = MaterialTheme.typography.titleMedium)
        var checked by remember { mutableStateOf(true) }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Minimal checkbox")
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }
        Text(if (checked) "Checkbox is checked" else "Checkbox is unchecked")

        Spacer(modifier = Modifier.height(32.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(32.dp))

        // --- 2. CheckboxParentExample (Advanced) ---
        Text("Advanced example", style = MaterialTheme.typography.titleMedium)

        // Стан для дочірніх чекбоксів
        val childCheckedStates = remember { mutableStateListOf(false, false, false) }

        // Обчислення стану батьківського чекбокса
        val parentState = when {
            childCheckedStates.all { it } -> ToggleableState.On
            childCheckedStates.none { it } -> ToggleableState.Off
            else -> ToggleableState.Indeterminate
        }

        Column {
            // Parent TriStateCheckbox
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Select all")
                TriStateCheckbox(
                    state = parentState,
                    onClick = {
                        val newState = parentState != ToggleableState.On
                        childCheckedStates.forEachIndexed { index, _ ->
                            childCheckedStates[index] = newState
                        }
                    }
                )
            }

            // Child Checkboxes
            childCheckedStates.forEachIndexed { index, isChecked ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 24.dp)
                ) {
                    Text("Option ${index + 1}")
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { newStatus ->
                            childCheckedStates[index] = newStatus
                        }
                    )
                }
            }
        }

        if (childCheckedStates.all { it }) {
            Text("All options selected", color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(48.dp))

        // КНОПКА НАЗАД
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBack
        ) {
            Text(stringResource(Res.string.back))
        }
    }
}