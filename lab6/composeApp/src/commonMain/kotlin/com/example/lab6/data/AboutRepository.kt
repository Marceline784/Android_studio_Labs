package com.example.lab6.data

import com.example.lab6.Platform
import com.example.lab6.data.preferences.Preferences
import kotlinx.datetime.*

class AboutRepository(
    private val platform: Platform,
    private val preferences: Preferences
) {

    fun getSystemInfo(): List<Pair<String, String>> =
        listOf(
            "Operating System" to "${platform.osName} ${platform.osVersion}",
            "Device Model" to platform.deviceModel,
            "CPU Type" to platform.cpuType,
            "Display" to "${platform.screen.width}x${platform.screen.height}"
        )

    fun increaseVisitCount() {
        preferences.aboutVisitedCount += 1
    }

    fun getVisitCount(): Int =
        preferences.aboutVisitedCount

    fun updateVisitedDate() {
        val now = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())

        preferences.aboutVisitedDate = now.toString()
    }

    fun getVisitedDate(): LocalDateTime? =
        preferences.aboutVisitedDate?.let {
            LocalDateTime.parse(it)
        }
}