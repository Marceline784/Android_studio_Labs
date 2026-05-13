package com.example.lab1.ui.screens.lab4

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.lab1.TimeZoneHelperImpl
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun AddTimeZoneDialog(onAdd: (String) -> Unit, onDismiss: () -> Unit) {
    val timezoneHelper = remember { TimeZoneHelperImpl() }
    val allZones = remember { timezoneHelper.getTimeZoneStrings() }
    var searchQuery by remember { mutableStateOf("") }

    val filteredZones = allZones.filter { it.contains(searchQuery, ignoreCase = true) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Додати часову зону", style = MaterialTheme.typography.headlineSmall)

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Пошук міста...") },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                )

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(filteredZones) { zone ->
                        Text(
                            text = zone,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onAdd(zone) }
                                .padding(12.dp)
                        )
                        HorizontalDivider(thickness = 0.5.dp)
                    }
                }

                TextButton(onClick = onDismiss, modifier = Modifier.align(androidx.compose.ui.Alignment.End)) {
                    Text("Скасувати")
                }
            }
        }
    }
}