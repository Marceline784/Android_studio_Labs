package com.example.lab6.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Lab9Screen(
    onBack: () -> Unit,
    viewModel: AboutViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    var showData by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(
                onClick = onBack,
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("← Назад")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Лабораторна робота №9",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Multiplatform Settings",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                showData = !showData
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = if (showData) "Сховати дані" else "Показати дані",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (showData) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Кількість відвідувань:",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = state.visitedCount.toString(),
                        fontSize = 32.sp
                    )

                    Divider()

                    Text(
                        text = "Останнє відкриття:",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = state.visitedDate,
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}