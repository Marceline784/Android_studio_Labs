package com.example.lab1.ui.screens.lab4

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lab1.TimeZoneHelperImpl
import kotlinx.coroutines.delay

@Composable
fun TimeZonePage(selectedTimeZones: List<String>) {
    val timezoneHelper = remember { TimeZoneHelperImpl() }
    var tick by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(60000)
            tick++
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            LocalTimeCard(
                city = timezoneHelper.currentTimeZone(),
                time = timezoneHelper.currentTime(),
                date = timezoneHelper.getDate(timezoneHelper.currentTimeZone())
            )
        }
        item {
            Text("Вибрані зони", style = MaterialTheme.typography.titleMedium)
        }
        items(selectedTimeZones) { zone ->
            TimeCard(
                timezone = zone,
                hours = timezoneHelper.hoursFromTimeZone(zone),
                time = timezoneHelper.getTime(zone),
                date = timezoneHelper.getDate(zone)
            )
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
fun TimeCard(timezone: String, hours: Double, time: String, date: String) {
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
            Column {
                Text(timezone, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                // Виправлено: перетворення Double в String
                Text("${if (hours >= 0) "+" else ""}${hours.toInt()} год від місцевого", style = MaterialTheme.typography.bodySmall)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(time, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text(date, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}