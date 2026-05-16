package com.example.lab6.data

import com.example.lab6.Platform

class AboutRepository {
    private val platform = Platform()

    fun getSystemInfo(): List<Pair<String, String>> {
        return listOf(
            "Operating System" to "${platform.osName} ${platform.osVersion}",
            "Device Model" to platform.deviceModel,
            "CPU Type" to platform.cpuType,
            "Display" to "${platform.screen.width}x${platform.screen.height} (DPI: ${platform.screen.density})"
        )
    }
}