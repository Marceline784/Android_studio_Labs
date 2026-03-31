package com.example.lab1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
// Імпорти ваших ресурсів
import lab1.composeapp.generated.resources.Res
import lab1.composeapp.generated.resources.abeezee_regular
import lab1.composeapp.generated.resources.abeezee_italic
import lab1.composeapp.generated.resources.acme_regular
import org.jetbrains.compose.resources.Font

@Composable
fun getAppTypography(): Typography {
    val bodyFontFamily = FontFamily(
        Font(Res.font.abeezee_regular, FontWeight.Normal),
        Font(Res.font.abeezee_italic, FontWeight.Normal)
    )

    val displayFontFamily = FontFamily(
        Font(Res.font.acme_regular, FontWeight.Normal)
    )

    val baseline = Typography()

    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
    )
}