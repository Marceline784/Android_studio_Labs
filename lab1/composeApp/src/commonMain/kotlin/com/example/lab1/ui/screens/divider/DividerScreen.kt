package com.example.lab1.ui.screens.divider

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
fun DividerScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Divider Components", style = MaterialTheme.typography.headlineLarge)

        // 1. ГОРИЗОНТАЛЬНИЙ РОЗДІЛЮВАЧ (HorizontalDivider)
        Text("Horizontal Divider:", style = MaterialTheme.typography.titleMedium)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("First item in list")
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Text("Second item in list")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. ВЕРТИКАЛЬНИЙ РОЗДІЛЮВАЧ (VerticalDivider)
        Text("Vertical Divider:", style = MaterialTheme.typography.titleMedium)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min), // Важливо: дозволяє розділювачу прийняти висоту тексту
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Item Left")
            VerticalDivider(
                modifier = Modifier.padding(horizontal = 8.dp),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.secondary
            )
            Text("Item Right")
        }

        Spacer(modifier = Modifier.weight(1f))

        // КНОПКА НАЗАД
        Button(modifier = Modifier.fillMaxWidth(), onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}