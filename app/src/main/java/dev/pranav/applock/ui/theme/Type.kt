package dev.pranav.applock.ui.theme

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Nothing OS Typography System
// Monospace serves as the NDot 55 / Geist Mono fallback for Dot-Matrix elements.
val DotMatrixFontFamily = FontFamily.Monospace

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
val Typography = Typography(
    // Large Header / Display (NDot 55 style)
    displayLarge = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 36.sp,
        letterSpacing = 2.sp,
        color = NothingTextPrimary
    ),
    displayMedium = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 30.sp,
        letterSpacing = 1.8.sp,
        color = NothingTextPrimary
    ),
    displaySmall = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 1.5.sp,
        color = NothingTextPrimary
    ),

    // Headlines
    headlineLarge = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 1.2.sp,
        color = NothingTextPrimary
    ),
    headlineMedium = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 1.0.sp,
        color = NothingTextPrimary
    ),

    // Titles
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.2.sp,
        color = NothingTextPrimary
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.15.sp,
        color = NothingTextPrimary
    ),
    titleMediumEmphasized = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 1.0.sp,
        color = NothingTextPrimary
    ),
    titleLargeEmphasized = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 1.2.sp,
        color = NothingTextPrimary
    ),

    // Body Text
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.25.sp,
        color = NothingTextPrimary
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.2.sp,
        color = NothingTextSecondary
    ),
    bodySmall = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.8.sp,
        color = NothingTextSecondary
    ),

    // Labels & Badges
    labelLarge = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 1.0.sp,
        color = NothingTextPrimary
    ),
    labelMedium = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.8.sp,
        color = NothingTextPrimary
    ),
    labelSmall = TextStyle(
        fontFamily = DotMatrixFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 9.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.6.sp,
        color = NothingTextSecondary
    )
)
