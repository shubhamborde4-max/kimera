package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val KimeraLightColorScheme = lightColorScheme(
    primary = KimeraCoral,
    onPrimary = KimeraWhite,
    primaryContainer = KimeraSoftPeach,
    onPrimaryContainer = KimeraCharcoal,
    secondary = KimeraDeepCoral,
    onSecondary = KimeraWhite,
    secondaryContainer = KimeraBadgePink,
    onSecondaryContainer = KimeraDeepCoral,
    tertiary = KimeraSoftPeach,
    onTertiary = KimeraCharcoal,
    background = KimeraWarmCream,
    onBackground = KimeraCharcoal,
    surface = KimeraIvory,
    onSurface = KimeraCharcoal,
    surfaceVariant = KimeraWarmSand,
    onSurfaceVariant = KimeraWarmGrey,
    outline = KimeraWarmSand,
    outlineVariant = KimeraWarmGrey.copy(alpha = 0.3f)
)

@Composable
fun KimeraTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = KimeraLightColorScheme,
        typography = KimeraTypography,
        content = content
    )
}
