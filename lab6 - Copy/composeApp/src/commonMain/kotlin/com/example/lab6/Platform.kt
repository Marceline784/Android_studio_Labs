package com.example.lab6

import io.github.aakira.napier.Napier

// Інформація про екран
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ScreenInfo() {
    val width: Int
    val height: Int
    val density: Int?
}

// Головний клас платформи
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Platform() {
    val osName: String
    val osVersion: String
    val deviceModel: String
    val cpuType: String
    val screen: ScreenInfo

    fun logSystemInfo()
}