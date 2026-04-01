package com.example.lab1.ui.screens.progress

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*

@Composable
fun ProgressScreen(onBack: () -> Unit) {
    val scope = rememberCoroutineScope()

    // Стан для визначеного прогресу
    var currentProgress by remember { mutableFloatStateOf(0f) }
    var isDeterminateLoading by remember { mutableStateOf(false) }

    // Стан для невизначеного прогресу
    var isIndeterminateLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Progress Indicators", style = MaterialTheme.typography.headlineLarge)

        // 1. DETERMINATE (LINEAR)
        Text("Determinate (Linear):", style = MaterialTheme.typography.titleMedium)
        Button(
            onClick = {
                isDeterminateLoading = true
                scope.launch {
                    currentProgress = 0f
                    for (i in 1..100) {
                        delay(50) // Імітація завантаження
                        currentProgress = i.toFloat() / 100
                    }
                    isDeterminateLoading = false
                }
            },
            enabled = !isDeterminateLoading
        ) {
            Text("Start Linear Loading")
        }

        if (isDeterminateLoading) {
            LinearProgressIndicator(
                progress = { currentProgress },
                modifier = Modifier.fillMaxWidth()
            )
            Text("${(currentProgress * 100).toInt()}%")
        }

        HorizontalDivider()

        // 2. INDETERMINATE (CIRCULAR)
        Text("Indeterminate (Circular):", style = MaterialTheme.typography.titleMedium)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { isIndeterminateLoading = !isIndeterminateLoading }) {
                Text(if (isIndeterminateLoading) "Stop" else "Start Circular")
            }

            if (isIndeterminateLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }

        HorizontalDivider()

        // 3. DETERMINATE (CIRCULAR)
        Text("Determinate (Circular):", style = MaterialTheme.typography.titleMedium)
        if (isDeterminateLoading) {
            CircularProgressIndicator(progress = { currentProgress })
        } else {
            Text("Натисніть першу кнопку, щоб побачити прогрес і тут", style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.weight(1f))

        // КНОПКА НАЗАД
        Button(modifier = Modifier.fillMaxWidth(), onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}