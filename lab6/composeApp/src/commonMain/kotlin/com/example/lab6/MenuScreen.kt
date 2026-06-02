package com.example.lab6

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(
    onLab6: () -> Unit,
    onLab7: () -> Unit,
    onLab8: () -> Unit,
    onLab9: () -> Unit,
    onLab10: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lab Menu", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        val buttonModifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        Button(onClick = onLab6, modifier = buttonModifier) { Text("Lab 6: System API") }
        Button(onClick = onLab7, modifier = buttonModifier) { Text("Lab 7: MVVM") }
        Button(onClick = onLab8, modifier = buttonModifier) { Text("Lab 8: Koin DI") }
        Button(onClick = onLab9, modifier = buttonModifier) { Text("Lab 9: Settings") }
        Button(onClick = onLab10, modifier = buttonModifier, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
            Text("Lab 10: SQLDelight Reminders")
        }
    }
}