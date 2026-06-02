package com.example.lab6.ui

data class AboutState(
    val platformInfo: List<Pair<String, String>> = emptyList(),
    val visitedCount: Int = 0,
    val visitedDate: String = "-----"
)