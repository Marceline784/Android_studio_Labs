package com.example.lab6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab6.ui.AboutScreen
import com.example.lab6.ui.Lab9Screen
import com.example.lab6.ui.theme.MyCustomTheme

@Composable
fun App() {

    var currentScreen by remember {
        mutableStateOf("menu")
    }

    MyCustomTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            when (currentScreen) {

                "menu" -> MainMenu(
                    onLab8Click = {
                        currentScreen = "lab8"
                    },
                    onLab9Click = {
                        currentScreen = "lab9"
                    }
                )

                "lab8" -> AboutScreen(
                    onBack = {
                        currentScreen = "menu"
                    }
                )

                "lab9" -> Lab9Screen(
                    onBack = {
                        currentScreen = "menu"
                    }
                )
            }
        }
    }
}

@Composable
fun MainMenu(
    onLab8Click: () -> Unit,
    onLab9Click: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E1E2F),
                        Color(0xFF2E3A59)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Мої лабораторні роботи",
                fontSize = 32.sp,
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Compose Multiplatform",
                fontSize = 20.sp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onLab8Click,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Лабораторна робота №8",
                    fontSize = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onLab9Click,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Лабораторна робота №9",
                    fontSize = 22.sp
                )
            }
        }
    }
}
