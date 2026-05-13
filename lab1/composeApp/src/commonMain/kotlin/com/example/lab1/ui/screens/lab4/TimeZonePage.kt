package com.example.lab1.ui.screens.lab4

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab1.TimeZoneHelperImpl
import kotlinx.coroutines.delay

@Composable
fun TimeZonePage(selectedTimeZones: MutableList<String>) {
    val timezoneHelper = remember { TimeZoneHelperImpl() }
    // Стан для примусового рекомпозиції кожну хвилину
    var tick by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(60000) // Оновлення раз на хвилину
            tick++
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Картка місцевого часу
        item {
            LocalTimeCard(
                city = timezoneHelper.currentTimeZone(),
                time = timezoneHelper.currentTime(),
                date = timezoneHelper.getDate(timezoneHelper.currentTimeZone())
            )
        }

        if (selectedTimeZones.isNotEmpty()) {
            item {
                Text(
                    text = "Вибрані зони",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            items(selectedTimeZones) { zone ->
                TimeCard(
                    timezone = zone,
                    hours = timezoneHelper.hoursFromTimeZone(zone),
                    time = timezoneHelper.getTime(zone),
                    date = timezoneHelper.getDate(zone),
                    onDelete = { selectedTimeZones.remove(zone) } // Додано видалення
                )
            }
        } else {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Список порожній. Натисніть + щоб додати місто.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

@Composable
fun LocalTimeCard(city: String, time: String, date: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Ваша локація", style = MaterialTheme.typography.labelMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(city, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text(date, style = MaterialTheme.typography.bodySmall)
                }
                Text(time, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Black)
            }
        }
    }
}

@Composable
fun TimeCard(timezone: String, hours: Double, time: String, date: String, onDelete: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(timezone, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                val diff = hours.toInt()
                Text(
                    text = "${if (diff >= 0) "+" else ""}$diff год від місцевого",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(horizontalAlignment = Alignment.End, modifier = Modifier.padding(end = 8.dp)) {
                    Text(time, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                    Text(date, style = MaterialTheme.typography.labelSmall)
                }
                // Кнопка видалення (зручно для менеджменту списку)
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Видалити",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}