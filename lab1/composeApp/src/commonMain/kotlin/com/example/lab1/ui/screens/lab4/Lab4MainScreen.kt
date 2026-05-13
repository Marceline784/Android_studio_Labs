@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab1.ui.screens.lab4

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab1.TimeZoneHelperImpl

@Composable
fun Lab4MainScreen(onBack: () -> Unit) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val selectedTimeZones = remember { mutableStateListOf<String>() }
    val showDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Світовий час") },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                        }
                    }
                )
                TabRow(selectedTabIndex = selectedTab) {
                    Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                        Text("Часові зони", modifier = Modifier.padding(16.dp))
                    }
                    Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                        Text("Пошук зустрічі", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        },
        floatingActionButton = {
            if (selectedTab == 0) {
                FloatingActionButton(onClick = { showDialog.value = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Додати")
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (selectedTab == 0) {
                TimeZonePage(selectedTimeZones)
            } else {
                MeetingFinderPage(selectedTimeZones)
            }

            if (showDialog.value) {
                AddTimeZoneDialog(
                    onAdd = { zone ->
                        if (!selectedTimeZones.contains(zone)) {
                            selectedTimeZones.add(zone)
                        }
                        showDialog.value = false
                    },
                    onDismiss = { showDialog.value = false }
                )
            }
        }
    }
}

@Composable
fun HourSelector(label: String, hour: Int, onHourChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Text(label, modifier = Modifier.width(100.dp))
        IconButton(onClick = { if (hour > 0) onHourChange(hour - 1) }) {
            Text("-", style = MaterialTheme.typography.headlineMedium)
        }
        Text(
            text = hour.toString().padStart(2, '0') + ":00",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        IconButton(onClick = { if (hour < 23) onHourChange(hour + 1) }) {
            Text("+", style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun MeetingFinderPage(selectedTimeZones: List<String>) {
    val timezoneHelper = remember { TimeZoneHelperImpl() }

    val startHour = remember { mutableIntStateOf(9) }
    val endHour = remember { mutableIntStateOf(17) }

    // Стан для обраних ЧЕКБОКСАМИ поясів
    val zonesToCompare = remember { mutableStateListOf<String>() }

    val showResultDialog = remember { mutableStateOf(false) }
    val meetingResults = remember { mutableStateOf<List<Int>>(emptyList()) }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text("Налаштування зустрічі", style = MaterialTheme.typography.titleMedium)

        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                HourSelector("Початок:", startHour.intValue) { startHour.intValue = it }
                HourSelector("Кінець:", endHour.intValue) { endHour.intValue = it }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Оберіть зони для врахування:", style = MaterialTheme.typography.titleSmall)

        // Список з вибором зон
        Card(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(vertical = 8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
            if (selectedTimeZones.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Список порожній", style = MaterialTheme.typography.bodyMedium)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    items(selectedTimeZones) { zone ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (zonesToCompare.contains(zone)) zonesToCompare.remove(zone)
                                    else zonesToCompare.add(zone)
                                }
                                .padding(4.dp)
                        ) {
                            Checkbox(
                                checked = zonesToCompare.contains(zone),
                                onCheckedChange = { checked ->
                                    if (checked == true) zonesToCompare.add(zone)
                                    else zonesToCompare.remove(zone)
                                }
                            )
                            Text(zone, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Викликаємо пошук тільки для тих зон, де стоїть галочка
                meetingResults.value = timezoneHelper.search(
                    startHour.intValue,
                    endHour.intValue,
                    zonesToCompare.toList()
                )
                showResultDialog.value = true
            },
            modifier = Modifier.fillMaxWidth(),
            // Кнопка активна, тільки якщо вибрано хоча б одну зону галочкою
            enabled = zonesToCompare.isNotEmpty()
        ) {
            Text("Знайти підходящий час")
        }

        if (selectedTimeZones.isEmpty()) {
            Text(
                "Додайте часові зони на першій вкладці, щоб почати пошук",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (showResultDialog.value) {
            AlertDialog(
                onDismissRequest = { showResultDialog.value = false },
                title = { Text("Результат пошуку") },
                text = {
                    if (meetingResults.value.isEmpty()) {
                        Text("На жаль, спільного часу для зустрічі не знайдено.")
                    } else {
                        Column {
                            Text("Підходящі години (за вашим часом):")
                            Text(
                                meetingResults.value.joinToString(", ") { "$it:00" },
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showResultDialog.value = false }) { Text("ОК") }
                }
            )
        }
    }
}