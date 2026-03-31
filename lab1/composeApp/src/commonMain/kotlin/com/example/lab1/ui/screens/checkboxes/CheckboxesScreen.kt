package com.example.lab1.ui.screens.checkboxes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*

@Composable
fun CheckboxesScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.checkboxes),
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}