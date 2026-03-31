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

@Composable
fun MainScreen(onNavigateToButtons: () -> Unit, onNavigateToCheckboxes: () -> Unit) {
    var showContent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { showContent = !showContent }, modifier = Modifier.padding(top = 16.dp)) {
            Text(if (showContent) "Hide Time" else "Show Time")
        }

        AnimatedVisibility(showContent) {
            val currentTime = TimeZoneHelperImpl().currentTime()
            Text(text = "Time: $currentTime", style = MaterialTheme.typography.displayMedium)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onClick = onNavigateToButtons
        ) {
            // Тепер використовуємо назву Buttons, як у викладача
            Text(stringResource(Res.string.buttons))
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onClick = onNavigateToCheckboxes
        ) {
            Text(stringResource(Res.string.checkboxes))
        }
    }
}