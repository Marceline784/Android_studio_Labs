package com.example.lab1.ui.screens.switches

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*

@Composable
fun SwitchScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Switch Components", style = MaterialTheme.typography.headlineLarge)

        // 1. Minimal Example
        Column {
            Text("1. Minimal Switch", style = MaterialTheme.typography.titleMedium)
            var checked1 by remember { mutableStateOf(false) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(
                    checked = checked1,
                    onCheckedChange = { checked1 = it }
                )
                Spacer(Modifier.width(12.dp))
                Text(if (checked1) "Enabled" else "Disabled")
            }
        }

        HorizontalDivider()

        // 2. Switch with Icon (Advanced)
        Column {
            Text("2. Switch with Icon", style = MaterialTheme.typography.titleMedium)
            var checked2 by remember { mutableStateOf(true) }
            Switch(
                checked = checked2,
                onCheckedChange = { checked2 = it },
                thumbContent = if (checked2) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else null
            )
        }

        HorizontalDivider()

        // 3. Switch with Custom Colors
        Column {
            Text("3. Custom Colors", style = MaterialTheme.typography.titleMedium)
            var checked3 by remember { mutableStateOf(true) }
            Switch(
                checked = checked3,
                onCheckedChange = { checked3 = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // КНОПКА НАЗАД
        Button(modifier = Modifier.fillMaxWidth(), onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}