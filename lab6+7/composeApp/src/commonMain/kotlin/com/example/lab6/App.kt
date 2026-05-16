package com.example.lab6

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab6.ui.AboutViewModel
import com.example.lab6.ui.theme.MyCustomTheme
import io.github.aakira.napier.Napier
import lab6.composeapp.generated.resources.Res
import lab6.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

// Перелік екранів для навігації
enum class Screen {
    Menu, Lab6, Lab7
}

@Composable
fun App(viewModel: AboutViewModel = viewModel { AboutViewModel() }) {
    var currentScreen by remember { mutableStateOf(Screen.Menu) }

    MyCustomTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            when (currentScreen) {
                Screen.Menu -> MainMenu { currentScreen = it }
                Screen.Lab6 -> Lab6Screen { currentScreen = Screen.Menu }
                Screen.Lab7 -> Lab7Screen(viewModel) { currentScreen = Screen.Menu }
            }
        }
    }
}

@Composable
fun MainMenu(onNavigate: (Screen) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Мої Лабораторні", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onNavigate(Screen.Lab6) }, modifier = Modifier.width(200.dp)) {
            Text("Лабораторна №6")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onNavigate(Screen.Lab7) }, modifier = Modifier.width(200.dp)) {
            Text("Лабораторна №7")
        }
    }
}

@Composable
fun Lab6Screen(onBack: () -> Unit) {
    var showContent by remember { mutableStateOf(false) }
    val greeting = remember { Greeting().greet() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) { Text("<- Назад") }

        Text("Лабораторна №6", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            showContent = !showContent
            // Перевірка логування Napier
            Napier.d("Lab6: натиснуто кнопку. Стан: $showContent", tag = "LAB6")
        }) {
            Text("Click me!")
        }

        AnimatedVisibility(showContent) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}

@Composable
fun Lab7Screen(viewModel: AboutViewModel, onBack: () -> Unit) {
    // Отримуємо дані через StateFlow з ViewModel
    val systemInfo by viewModel.state.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextButton(onClick = onBack) { Text("<- Назад") }

        Text("Лабораторна №7 (MVVM)",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(16.dp))

        // Виведення даних, які ViewModel отримала з Repository
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(systemInfo) { info ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(info.first, style = MaterialTheme.typography.labelLarge)
                        Text(info.second, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}