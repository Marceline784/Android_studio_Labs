package com.example.lab6

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab6.ui.theme.MyCustomTheme
import io.github.aakira.napier.Napier // Додано імпорт
import lab6.composeapp.generated.resources.Res
import lab6.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    val platform = remember { Platform() } // Створюємо об'єкт платформи

    MyCustomTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = "Інформація про систему", style = MaterialTheme.typography.headlineMedium)

                // Дані з нашого Platform API
                Text("Система: ${platform.osName} ${platform.osVersion}")
                Text("Пристрій: ${platform.deviceModel}")
                Text("Екран: ${platform.screen.width}x${platform.screen.height}")

                Button(onClick = {
                    platform.logSystemInfo() // Виклик логування через Napier
                }) {
                    Text("Залогувати в консоль")
                }
            }
        }
    }
}