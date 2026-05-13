package com.example.lab1.dialogs

import androidx.compose.runtime.Composable

@Composable
expect fun AddTimeDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit)

@Composable
expect fun MeetingDialogWrapper(onDismiss: () -> Unit, content: @Composable () -> Unit)