package com.example.lab1.ui.screens.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import lab1.composeapp.generated.resources.*

@Composable
fun DialogScreen(onBack: () -> Unit) {
    var openAlertDialog by remember { mutableStateOf(false) }
    var openMinimalDialog by remember { mutableStateOf(false) }
    var openImageDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Dialog Components", style = MaterialTheme.typography.headlineLarge)

        // 1. Кнопка для Alert Dialog
        Button(onClick = { openAlertDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Show Alert Dialog")
        }

        // 2. Кнопка для Minimal Dialog
        Button(onClick = { openMinimalDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Show Minimal Dialog")
        }

        // 3. Кнопка для Dialog with Image
        Button(onClick = { openImageDialog = true }, modifier = Modifier.fillMaxWidth()) {
            Text("Show Dialog with Image")
        }

        Spacer(modifier = Modifier.weight(1f))

        // КНОПКА НАЗАД
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = onBack) {
            Text(stringResource(Res.string.back))
        }

        // --- ЛОГІКА ВІДОБРАЖЕННЯ ДІАЛОГІВ ---

        if (openAlertDialog) {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = { openAlertDialog = false },
                dialogTitle = "Alert dialog example",
                dialogText = "This is an example of an alert dialog with buttons.",
                icon = Icons.Default.Info
            )
        }

        if (openMinimalDialog) {
            MinimalDialog(onDismissRequest = { openMinimalDialog = false })
        }

        if (openImageDialog) {
            DialogWithImage(
                onDismissRequest = { openImageDialog = false },
                onConfirmation = { openImageDialog = false },
                painter = painterResource(Res.drawable.compose_multiplatform), // Використовуємо стандартне лого
                imageDescription = "Example image"
            )
        }
    }
}

// ПРИКЛАДИ З ТВОЄЇ ДОКУМЕНТАЦІЇ

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = { Icon(icon, contentDescription = "Example Icon") },
        title = { Text(text = dialogTitle) },
        text = { Text(text = dialogText) },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmation) { Text("Confirm") }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) { Text("Dismiss") }
        }
    )
}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier.fillMaxWidth().height(200.dp).padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun DialogWithImage(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter,
    imageDescription: String,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier.fillMaxWidth().height(375.dp).padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painter,
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(160.dp)
                )
                Text(
                    text = "This is a dialog with buttons and an image.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(onClick = onDismissRequest, modifier = Modifier.padding(8.dp)) {
                        Text("Dismiss")
                    }
                    TextButton(onClick = onConfirmation, modifier = Modifier.padding(8.dp)) {
                        Text("Confirm")
                    }
                }
            }
        }
    }
}