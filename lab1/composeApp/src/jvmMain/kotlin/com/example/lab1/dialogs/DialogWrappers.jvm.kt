package com.example.lab1.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.rememberDialogState

@Composable
actual fun AddTimeDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    DialogWindow(
        onCloseRequest = onDismiss,
        state = rememberDialogState(),
        title = "Додати місто",
        content = { content() }
    )
}

@Composable
actual fun MeetingDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    DialogWindow(
        onCloseRequest = onDismiss,
        state = rememberDialogState(),
        title = "Результати",
        content = { content() }
    )
}