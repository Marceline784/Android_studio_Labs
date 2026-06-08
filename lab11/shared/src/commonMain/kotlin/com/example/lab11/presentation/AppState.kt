package com.example.lab11.presentation

data class AppState(
    val isLoading: Boolean = false,
    val successMessage: String = "",
    val errorMessage: String = ""
)