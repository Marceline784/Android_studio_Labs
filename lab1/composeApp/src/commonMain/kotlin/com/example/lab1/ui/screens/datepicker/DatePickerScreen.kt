@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lab1.ui.screens.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePickerScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text("Date Pickers", style = MaterialTheme.typography.headlineLarge)

        // 1. DOCKED DATE PICKER
        Text("1. Docked Date Picker", style = MaterialTheme.typography.titleMedium)
        DatePickerDocked()

        HorizontalDivider()

        // 2. MODAL DATE PICKER
        Text("2. Modal Date Picker", style = MaterialTheme.typography.titleMedium)
        var showModal by remember { mutableStateOf(false) }
        var selectedDateModal by remember { mutableStateOf<Long?>(null) }

        Button(onClick = { showModal = true }) {
            Text("Open Modal Picker")
        }
        Text("Selected: ${selectedDateModal?.let { convertMillisToDate(it) } ?: "None"}")

        if (showModal) {
            DatePickerModal(
                onDateSelected = { selectedDateModal = it },
                onDismiss = { showModal = false }
            )
        }

        HorizontalDivider()

        // 3. DATE RANGE PICKER
        Text("3. Date Range Picker", style = MaterialTheme.typography.titleMedium)
        var showRangeModal by remember { mutableStateOf(false) }
        var selectedRange by remember { mutableStateOf<Pair<Long?, Long?>>(null to null) }

        Button(onClick = { showRangeModal = true }) {
            Text("Open Range Picker")
        }
        Text("Range: ${selectedRange.first?.let { convertMillisToDate(it) } ?: "..." } - ${selectedRange.second?.let { convertMillisToDate(it) } ?: "..." }")

        if (showRangeModal) {
            DateRangePickerModal(
                onDateRangeSelected = { selectedRange = it },
                onDismiss = { showRangeModal = false }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // КНОПКА НАЗАД
        Button(modifier = Modifier.fillMaxWidth(), onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}

// ДОПОМІЖНІ КОМПОНЕНТИ З ТВОЄЇ ДОКУМЕНТАЦІЇ

@Composable
fun DatePickerDocked() {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("Date of Birth") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Select date")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp)
                        .shadow(elevation = 8.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(state = datePickerState, showModeToggle = false)
                }
            }
        }
    }
}

@Composable
fun DatePickerModal(onDateSelected: (Long?) -> Unit, onDismiss: () -> Unit) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = { onDateSelected(datePickerState.selectedDateMillis); onDismiss() }) { Text("OK") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun DateRangePickerModal(onDateRangeSelected: (Pair<Long?, Long?>) -> Unit, onDismiss: () -> Unit) {
    val dateRangePickerState = rememberDateRangePickerState()
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateRangeSelected(dateRangePickerState.selectedStartDateMillis to dateRangePickerState.selectedEndDateMillis)
                onDismiss()
            }) { Text("OK") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            modifier = Modifier.fillMaxWidth().height(500.dp).padding(16.dp)
        )
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}