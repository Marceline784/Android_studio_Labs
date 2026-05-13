package com.example.lab1.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
actual fun AddTimeDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        content()
    }
}

@Composable
actual fun MeetingDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        content()
    }
}