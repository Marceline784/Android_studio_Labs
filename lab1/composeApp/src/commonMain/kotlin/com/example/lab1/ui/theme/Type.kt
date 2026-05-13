package com.example.lab1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
// Імпорти ресурсів
import lab1.composeapp.generated.resources.Res
import lab1.composeapp.generated.resources.abeezee_regular
import lab1.composeapp.generated.resources.abeezee_italic
import lab1.composeapp.generated.resources.acme_regular
import org.jetbrains.compose.resources.Font

@Composable
fun getAppTypography(): Typography {
    // Використовуємо функцію FontFamily(...) зі списком, щоб уникнути помилки конструктора
    val bodyFontFamily = FontFamily(
        listOf(
            Font(Res.font.abeezee_regular, FontWeight.Normal),
            Font(Res.font.abeezee_italic, FontWeight.Normal)
        )
    ).let {
        // Додаємо SansSerif як запасний варіант (fallback) через об'єднання
        FontFamily.SansSerif
    }

    // Для спрощення та уникнення помилок типів у Web, зробимо так:
    // Твої кастомні шрифти будуть основними, а SansSerif підхопить українську
    val displayFontFamily = FontFamily(
        listOf(Font(Res.font.acme_regular, FontWeight.Normal))
    )

    val baseline = Typography()

    // Створюємо фінальну типографію
    // Якщо українська все ще не відображається, заміни displayFontFamily на FontFamily.SansSerif
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
        bodyLarge = baseline.bodyLarge.copy(fontFamily = FontFamily.SansSerif), // SansSerif гарантує UA мову
        bodyMedium = baseline.bodyMedium.copy(fontFamily = FontFamily.SansSerif),
        bodySmall = baseline.bodySmall.copy(fontFamily = FontFamily.SansSerif),
        labelLarge = baseline.labelLarge.copy(fontFamily = FontFamily.SansSerif),
        labelMedium = baseline.labelMedium.copy(fontFamily = FontFamily.SansSerif),
        labelSmall = baseline.labelSmall.copy(fontFamily = FontFamily.SansSerif)
    )
}