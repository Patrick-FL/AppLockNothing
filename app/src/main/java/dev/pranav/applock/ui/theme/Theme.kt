package dev.pranav.applock.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

// Nothing OS Dark-First Color Scheme (Strictly Achromatic with Functional Accents)
private val NothingColorScheme = darkColorScheme(
    primary = NothingTextPrimary,
    onPrimary = NothingBlack,
    primaryContainer = NothingSurface,
    onPrimaryContainer = NothingTextPrimary,
    secondary = NothingSage,
    onSecondary = NothingBlack,
    secondaryContainer = NothingSurface,
    onSecondaryContainer = NothingSage,
    tertiary = NothingRed,
    onTertiary = NothingTextPrimary,
    background = NothingBlack,
    onBackground = NothingTextPrimary,
    surface = NothingSurface,
    onSurface = NothingTextPrimary,
    surfaceVariant = NothingDarkGray,
    onSurfaceVariant = NothingTextSecondary,
    outline = NothingBorder,
    outlineVariant = NothingBorder,
    error = NothingRed,
    onError = NothingTextPrimary
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppLockTheme(
    darkTheme: Boolean = true, // Force Nothing OS Dark First
    dynamicColor: Boolean = false, // Disable dynamic Material You colors to maintain Nothing OS identity
    content: @Composable () -> Unit
) {
    val shapes = Shapes(
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(20.dp),
        large = RoundedCornerShape(28.dp),
        extraLarge = RoundedCornerShape(36.dp),
        largeIncreased = CircleShape
    )

    MaterialExpressiveTheme(
        colorScheme = NothingColorScheme,
        typography = Typography,
        shapes = shapes,
        content = content,
        motionScheme = MotionScheme.expressive()
    )
}
