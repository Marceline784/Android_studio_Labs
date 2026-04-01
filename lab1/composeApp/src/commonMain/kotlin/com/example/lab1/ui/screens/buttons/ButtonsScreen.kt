package com.example.lab1.ui.screens.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*

@Composable
fun ButtonsScreen(
    onBack: () -> Unit,             // Функція для кнопки Назад
    onShowMessage: (String) -> Unit // Функція для показу повідомлення (Snackbar)
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()), // Додаємо прокрутку, якщо кнопок буде багато
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp) // Відступи між кнопками
    ) {
        Text(
            text = stringResource(Res.string.buttons),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // 1. Filled Button — Найвищий пріоритет (Головна дія)
        Button(onClick = { onShowMessage("Збережено (Filled Button)") }) {
            Text("Filled")
        }

        // 2. Filled Tonal Button — Високий пріоритет (Важлива дія)
        FilledTonalButton(onClick = { onShowMessage("Додано в кошик (Tonal Button)") }) {
            Text("Tonal")
        }

        // 3. Elevated Button — Середній пріоритет (Виділена кнопка з тінню)
        ElevatedButton(onClick = { onShowMessage("Важлива інформація (Elevated Button)") }) {
            Text("Elevated")
        }

        // 4. Outlined Button — Середній пріоритет (Другорядна дія)
        OutlinedButton(onClick = { onShowMessage("Вибір скасовано (Outlined Button)") }) {
            Text("Outlined")
        }

        // 5. Text Button — Низький пріоритет (Додаткова інформація)
        TextButton(onClick = { onShowMessage("Перехід до деталей... (Text Button)") }) {
            Text("Text Button")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // КНОПКА НАЗАД (використовуємо Outlined для повернення)
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onBack
        ) {
            Text(stringResource(Res.string.back))
        }
    }
}