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

        Button(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            onClick = onLab4Clicked
        ) {
            Text("Лабораторна №4 (Time Zones)")
        }
    }
}