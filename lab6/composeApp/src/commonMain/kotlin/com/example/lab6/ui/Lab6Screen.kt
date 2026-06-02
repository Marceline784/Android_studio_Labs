package com.example.lab6.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab6.Platform

@Composable
fun Lab6Screen(onBack: () -> Unit) {
    val platform = Platform()
    Scaffold { padding ->
        Column(Modifier.padding(padding).padding(16.dp)) {
            Text("System Information", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text("OS: ${platform.osName}")
            Text("Version: ${platform.osVersion}")
            Text("Device: ${platform.deviceModel}")
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onBack) { Text("Back") }
        }
    }
}