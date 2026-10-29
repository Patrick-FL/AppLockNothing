package dev.pranav.applock.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Nothing OS Light Mode Color Scheme (Light Background #F2F2F4, Solid Surface #FFFFFF, Dark Text #111111)
private val NothingLightColorScheme = lightColorScheme(
    primary = NothingLightTextPrimary,
    onPrimary = NothingLightSurface,
    primaryContainer = NothingLightSurface,
    onPrimaryContainer = NothingLightTextPrimary,
    secondary = NothingSage,
    onSecondary = NothingLightSurface,
    secondaryContainer = NothingLightSurface,
    onSecondaryContainer = NothingSage,
    tertiary = NothingRed,
    onTertiary = NothingLightSurface,
    background = NothingLightBackground,
    onBackground = NothingLightTextPrimary,
    surface = NothingLightSurface,
    onSurface = NothingLightTextPrimary,
    surfaceVariant = Color(0xFFE5E5E8),
    onSurfaceVariant = NothingLightTextSecondary,
    outline = NothingLightBorder,
    outlineVariant = NothingLightBorder,
    error = NothingRed,
    onError = NothingLightSurface
)

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppLockTheme(
    darkTheme: Boolean = false, // Enforce Nothing OS Light Mode
    dynamicColor: Boolean = false, // Disable Material You dynamic colors
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
        colorScheme = NothingLightColorScheme,
        typography = Typography,
        shapes = shapes,
        content = content,
        motionScheme = MotionScheme.expressive()
    )
}
