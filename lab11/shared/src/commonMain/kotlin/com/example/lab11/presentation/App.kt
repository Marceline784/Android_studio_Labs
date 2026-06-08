package com.example.lab11.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab11.ui.theme.MyCustomTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {

    MyCustomTheme {

        val viewModel = koinViewModel<AppViewModel>()
        val state by viewModel.state.collectAsState()

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "REST API Demo",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { viewModel.getPosts() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("GET")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.addPost() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("POST")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.updatePost() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("PUT")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.deletePost() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("DELETE")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (state.isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            if (state.successMessage.isNotEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Text(
                        text = state.successMessage,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            if (state.errorMessage.isNotEmpty()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {

                    Text(
                        text = state.errorMessage,
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}