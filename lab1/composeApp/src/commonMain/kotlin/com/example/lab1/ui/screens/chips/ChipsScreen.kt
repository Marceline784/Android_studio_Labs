package com.example.lab1.ui.screens.chips

import androidx.compose.foundation.clickable // ЦЕЙ ІМПОРТ ВИПРАВЛЯЄ ПОМИЛКУ
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*


@Composable
fun ChipsScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Chips Components", style = MaterialTheme.typography.headlineLarge)

        // 1. Assist Chip
        Text("Assist Chip:", style = MaterialTheme.typography.titleMedium)
        AssistChip(
            onClick = { /* Дія */ },
            label = { Text("Assist chip") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = null,
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )

        // 2. Filter Chip
        Text("Filter Chip:", style = MaterialTheme.typography.titleMedium)
        var selected by remember { mutableStateOf(false) }
        FilterChip(
            onClick = { selected = !selected },
            label = { Text("Filter chip") },
            selected = selected,
            leadingIcon = if (selected) {
                {
                    Icon(
                        Icons.Filled.Done,
                        contentDescription = null,
                        Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            } else null
        )

        // 3. Input Chip
        Text("Input Chip:", style = MaterialTheme.typography.titleMedium)
        var inputChipEnabled by remember { mutableStateOf(true) }

        if (inputChipEnabled) {
            InputChip(
                selected = true,
                onClick = { /* Дія при натисканні на сам чип */ },
                label = { Text("Input chip") },
                avatar = {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        Modifier.size(InputChipDefaults.AvatarSize)
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Dismiss",
                        modifier = Modifier
                            .size(InputChipDefaults.AvatarSize)
                            .padding(4.dp)
                            .clickable { inputChipEnabled = false } // Тепер 'clickable' працює
                    )
                }
            )
        } else {
            TextButton(onClick = { inputChipEnabled = true }) {
                Text("Відновити чип")
            }
        }

        // 4. Suggestion Chip
        Text("Suggestion Chip:", style = MaterialTheme.typography.titleMedium)
        SuggestionChip(
            onClick = { /* Дія */ },
            label = { Text("Suggestion chip") }
        )

        // 5. Elevated Assist Chip
        Text("Elevated Chip:", style = MaterialTheme.typography.titleMedium)
        ElevatedAssistChip(
            onClick = { /* Дія */ },
            label = { Text("Elevated Assist") },
            leadingIcon = { Icon(Icons.Filled.Star, contentDescription = null) }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // КНОПКА НАЗАД
        Button(modifier = Modifier.fillMaxWidth(), onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}