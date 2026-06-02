package com.example.lab6.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.compose.viewmodel.koinViewModel

class Lab7ViewModel : ViewModel() {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    fun increment() {
        _count.value += 1
    }
}

@Composable
fun Lab7Screen(
    onBack: () -> Unit,
    viewModel: Lab7ViewModel = koinViewModel()
) {
    val count by viewModel.count.collectAsStateWithLifecycle()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Lab 7: MVVM", style = MaterialTheme.typography.headlineMedium)
            Text("Current count: $count", style = MaterialTheme.typography.displayMedium)

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { viewModel.increment() }) {
                Text("Increment")
            }

            OutlinedButton(onClick = onBack) {
                Text("Back")
            }
        }
    }
}