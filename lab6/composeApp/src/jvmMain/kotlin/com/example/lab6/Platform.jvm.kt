package com.example.lab6

import io.github.aakira.napier.Napier
import java.awt.Toolkit

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ScreenInfo actual constructor() {
    private val toolkit = Toolkit.getDefaultToolkit().screenSize
    actual val width: Int = toolkit.width
    actual val height: Int = toolkit.height
    actual val density: Int? = Toolkit.getDefaultToolkit().screenResolution
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Platform actual constructor() {
    actual val osName: String = System.getProperty("os.name")
    actual val osVersion: String = System.getProperty("os.version")
    actual val deviceModel: String = "Desktop Workstation"
    actual val cpuType: String = System.getProperty("os.arch")
    actual val screen: ScreenInfo = ScreenInfo()

    actual fun logSystemInfo() {
        Napier.d("Desktop Info: $osName $osVersion, Arch: $cpuType", tag = "LAB6_SYSTEM")
    }
}