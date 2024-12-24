package com.tamersarioglu.weatherapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Light Theme Colors
private val LightPrimary = Color(0xFF2196F3)
private val LightSecondary = Color(0xFF03A9F4)
private val LightTertiary = Color(0xFF4CAF50)
private val LightBackground = Color(0xFFF5F5F5)
private val LightSurface = Color(0xFFFFFFFF)
private val LightError = Color(0xFFB00020)

// Dark Theme Colors
private val DarkPrimary = Color(0xFF82B1FF)
private val DarkSecondary = Color(0xFF80D8FF)
private val DarkTertiary = Color(0xFF80CBC4)
private val DarkBackground = Color(0xFF1A1A1A)
private val DarkSurface = Color(0xFF2D2D2D)
private val DarkError = Color(0xFFCF6679)

// Common Colors
private val OnLight = Color(0xFF000000)
private val OnDark = Color(0xFFFFFFFF)

val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = OnDark,
    secondary = DarkSecondary,
    onSecondary = OnDark,
    tertiary = DarkTertiary,
    onTertiary = OnDark,
    background = DarkBackground,
    onBackground = OnDark,
    surface = DarkSurface,
    onSurface = OnDark,
    error = DarkError,
    onError = OnDark
)

val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = OnLight,
    secondary = LightSecondary,
    onSecondary = OnLight,
    tertiary = LightTertiary,
    onTertiary = OnLight,
    background = LightBackground,
    onBackground = OnLight,
    surface = LightSurface,
    onSurface = OnLight,
    error = LightError,
    onError = OnDark
)

// You can keep your existing WeatherAppTheme composable as is
@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}