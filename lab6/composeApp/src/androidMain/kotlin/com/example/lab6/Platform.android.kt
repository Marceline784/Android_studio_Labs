package com.example.lab6

import android.os.Build
import io.github.aakira.napier.Napier

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ScreenInfo actual constructor() {
    actual val width: Int = android.content.res.Resources.getSystem().displayMetrics.widthPixels
    actual val height: Int = android.content.res.Resources.getSystem().displayMetrics.heightPixels
    actual val density: Int? = android.content.res.Resources.getSystem().displayMetrics.densityDpi
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Platform actual constructor() {
    actual val osName: String = "Android"
    actual val osVersion: String = Build.VERSION.RELEASE
    actual val deviceModel: String = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val cpuType: String = Build.SUPPORTED_ABIS.firstOrNull() ?: "Unknown"
    actual val screen: ScreenInfo = ScreenInfo()

    actual fun logSystemInfo() {
        Napier.d("Android Info: $osName $osVersion, Model: $deviceModel", tag = "LAB6_SYSTEM")
    }
}