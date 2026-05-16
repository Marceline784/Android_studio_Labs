package com.example.lab6.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font

// Імпорти ресурсів для проєкту lab6
import lab6.composeapp.generated.resources.Res
import lab6.composeapp.generated.resources.abeezee_regular
import lab6.composeapp.generated.resources.abeezee_italic
import lab6.composeapp.generated.resources.acme_regular

@Composable
fun getAppTypography(): Typography {
    // Шрифти для заголовків та акцентів
    val displayFontFamily = FontFamily(
        Font(Res.font.acme_regular, FontWeight.Normal)
    )

    // Шрифти для основного тексту
    val bodyFontFamily = FontFamily(
        Font(Res.font.abeezee_regular, FontWeight.Normal),
        Font(Res.font.abeezee_italic, FontWeight.Normal)
    )

    val baseline = Typography()

    return Typography(
        // Заголовки (Display)
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),

        // Другорядні заголовки (Headline)
        headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),

        // Назви (Title)
        titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),

        // Основний текст (Body)
        // Використовуємо SansSerif як надійний варіант для кирилиці
        bodyLarge = baseline.bodyLarge.copy(fontFamily = FontFamily.SansSerif),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = FontFamily.SansSerif),
        bodySmall = baseline.bodySmall.copy(fontFamily = FontFamily.SansSerif),

        // Підписи та ярлики (Label)
        labelLarge = baseline.labelLarge.copy(fontFamily = FontFamily.SansSerif),
        labelMedium = baseline.labelMedium.copy(fontFamily = FontFamily.SansSerif),
        labelSmall = baseline.labelSmall.copy(fontFamily = FontFamily.SansSerif)
    )
}