package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Bright Pinkish Theme Color Scheme
private val BugaBrightPinkColorScheme = lightColorScheme(
  primary = RosePrimary,
  onPrimary = RoseOnPrimary,
  primaryContainer = RosePrimaryContainer,
  onPrimaryContainer = RoseOnPrimaryContainer,
  secondary = LavenderSecondary,
  onSecondary = LavenderOnSecondary,
  secondaryContainer = LavenderSecondaryContainer,
  onSecondaryContainer = LavenderOnSecondaryContainer,
  tertiary = SageTertiary,
  onTertiary = SageOnTertiary,
  tertiaryContainer = SageTertiaryContainer,
  onTertiaryContainer = SageOnTertiaryContainer,
  background = CanvasSurface,
  onBackground = CanvasOnSurface,
  surface = CanvasSurface,
  onSurface = CanvasOnSurface,
  surfaceVariant = CanvasSurfaceVariant,
  onSurfaceVariant = CanvasOnSurfaceVariant,
  surfaceContainerLowest = CanvasSurfaceContainerLowest,
  surfaceContainerLow = CanvasSurfaceContainerLow,
  surfaceContainer = CanvasSurfaceContainer,
  surfaceContainerHigh = CanvasSurfaceContainerHigh,
  surfaceContainerHighest = CanvasSurfaceContainerHighest,
  inverseSurface = DeepPlumInverseSurface,
  inverseOnSurface = DeepPlumInverseOnSurface,
  outline = OutlineColor,
  outlineVariant = OutlineVariantColor,
  error = ErrorColor,
  errorContainer = ErrorContainerColor,
  onErrorContainer = OnErrorContainerColor
)

@Composable
fun BugaAtServiceTheme(
  darkTheme: Boolean = false, // Always bright theme with pinkish shade as explicitly requested
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = BugaBrightPinkColorScheme,
    typography = BugaTypography,
    content = content
  )
}
