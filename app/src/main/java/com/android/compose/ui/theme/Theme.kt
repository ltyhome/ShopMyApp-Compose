package com.android.compose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Material 3 color schemes
private val composeDarkColorScheme =
    darkColorScheme(
        primary = composeDarkPrimary,
        onPrimary = composeDarkOnPrimary,
        primaryContainer = composeDarkPrimaryContainer,
        onPrimaryContainer = composeDarkOnPrimaryContainer,
        inversePrimary = composeDarkPrimaryInverse,
        secondary = composeDarkSecondary,
        onSecondary = composeDarkOnSecondary,
        secondaryContainer = composeDarkSecondaryContainer,
        onSecondaryContainer = composeDarkOnSecondaryContainer,
        tertiary = composeDarkTertiary,
        onTertiary = composeDarkOnTertiary,
        tertiaryContainer = composeDarkTertiaryContainer,
        onTertiaryContainer = composeDarkOnTertiaryContainer,
        error = composeDarkError,
        onError = composeDarkOnError,
        errorContainer = composeDarkErrorContainer,
        onErrorContainer = composeDarkOnErrorContainer,
        background = composeDarkBackground,
        onBackground = composeDarkOnBackground,
        surface = composeDarkSurface,
        onSurface = composeDarkOnSurface,
        inverseSurface = composeDarkInverseSurface,
        inverseOnSurface = composeDarkInverseOnSurface,
        surfaceVariant = composeDarkSurfaceVariant,
        onSurfaceVariant = composeDarkOnSurfaceVariant,
        outline = composeDarkOutline)

private val composeLightColorScheme =
    lightColorScheme(
        primary = composeLightPrimary,
        onPrimary = composeLightOnPrimary,
        primaryContainer = composeLightPrimaryContainer,
        onPrimaryContainer = composeLightOnPrimaryContainer,
        inversePrimary = composeLightPrimaryInverse,
        secondary = composeLightSecondary,
        onSecondary = composeLightOnSecondary,
        secondaryContainer = composeLightSecondaryContainer,
        onSecondaryContainer = composeLightOnSecondaryContainer,
        tertiary = composeLightTertiary,
        onTertiary = composeLightOnTertiary,
        tertiaryContainer = composeLightTertiaryContainer,
        onTertiaryContainer = composeLightOnTertiaryContainer,
        error = composeLightError,
        onError = composeLightOnError,
        errorContainer = composeLightErrorContainer,
        onErrorContainer = composeLightOnErrorContainer,
        background = composeLightBackground,
        onBackground = composeLightOnBackground,
        surface = composeLightSurface,
        onSurface = composeLightOnSurface,
        inverseSurface = composeLightInverseSurface,
        inverseOnSurface = composeLightInverseOnSurface,
        surfaceVariant = composeLightSurfaceVariant,
        onSurfaceVariant = composeLightOnSurfaceVariant,
        outline = composeLightOutline)

@Composable
fun ComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
  val composeColorScheme =
      when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> composeDarkColorScheme
        else -> composeLightColorScheme
      }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = composeColorScheme.primary.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
      colorScheme = composeColorScheme,
      typography = composeTypography,
      shapes = shapes,
      content = content)
}
