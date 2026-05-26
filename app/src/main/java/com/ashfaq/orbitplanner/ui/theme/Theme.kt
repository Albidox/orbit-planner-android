package com.ashfaq.orbitplanner.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val OrbitDarkColorScheme = darkColorScheme(
    primary = OrbitCyan,
    onPrimary = OrbitDeepNavy,
    secondary = OrbitViolet,
    onSecondary = OrbitTextPrimary,
    tertiary = OrbitSunrise,
    onTertiary = OrbitDeepNavy,
    background = OrbitDeepNavy,
    onBackground = OrbitTextPrimary,
    surface = OrbitCardNavy,
    onSurface = OrbitTextPrimary,
    surfaceVariant = OrbitSpaceNavy,
    onSurfaceVariant = OrbitTextSecondary,
    outline = OrbitCardStroke,
    error = OrbitError
)

private val OrbitLightColorScheme = lightColorScheme(
    primary = OrbitSpaceNavy,
    onPrimary = OrbitTextPrimary,
    secondary = OrbitViolet,
    tertiary = OrbitSunrise,
    background = OrbitTextPrimary,
    onBackground = OrbitDeepNavy,
    surface = OrbitTextPrimary,
    onSurface = OrbitDeepNavy,
    surfaceVariant = OrbitTextSecondary,
    onSurfaceVariant = OrbitSpaceNavy,
    outline = OrbitCardStroke,
    error = OrbitError
)

@Composable
fun OrbitPlannerTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) OrbitDarkColorScheme else OrbitLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
