package com.example.lab6.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.lab6.ui.theme.MyCustomTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AboutScreen(
    onBack: () -> Unit,
    viewModel: AboutViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var showInfo by remember { mutableStateOf(false) }

    MyCustomTheme {
        Scaffold { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(20.dp)
            ) {
                Button(onClick = onBack) {
                    Text("Back")
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text("LAB 8", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
                Text("Koin Dependency Injection", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { showInfo = !showInfo },
                    modifier = Modifier.fillMaxWidth().height(55.dp)
                ) {
                    Text(if (showInfo) "Hide info" else "Show info")
                }

                Spacer(modifier = Modifier.height(20.dp))

                AnimatedVisibility(
                    visible = showInfo,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        state.platformInfo.forEach { info ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                // Тут ми використовуємо secondaryContainer, щоб колір картки був зеленим
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                                )
                            ) {
                                Column(Modifier.padding(16.dp)) {
                                    Text(
                                        text = info.first,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Text(
                                        text = info.second,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}