package com.example.compose
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.AppTypography
import com.visualrecursion.slidenotes.ui.theme.backgroundDark
import com.visualrecursion.slidenotes.ui.theme.backgroundDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.backgroundDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.backgroundLight
import com.visualrecursion.slidenotes.ui.theme.backgroundLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.backgroundLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.errorContainerDark
import com.visualrecursion.slidenotes.ui.theme.errorContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.errorContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.errorContainerLight
import com.visualrecursion.slidenotes.ui.theme.errorContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.errorContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.errorDark
import com.visualrecursion.slidenotes.ui.theme.errorDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.errorDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.errorLight
import com.visualrecursion.slidenotes.ui.theme.errorLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.errorLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.inverseOnSurfaceDark
import com.visualrecursion.slidenotes.ui.theme.inverseOnSurfaceDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.inverseOnSurfaceLight
import com.visualrecursion.slidenotes.ui.theme.inverseOnSurfaceLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.inverseOnSurfaceLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.inversePrimaryDark
import com.visualrecursion.slidenotes.ui.theme.inversePrimaryDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.inversePrimaryDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.inversePrimaryLight
import com.visualrecursion.slidenotes.ui.theme.inversePrimaryLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.inversePrimaryLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.inverseSurfaceDark
import com.visualrecursion.slidenotes.ui.theme.inverseSurfaceDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.inverseSurfaceDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.inverseSurfaceLight
import com.visualrecursion.slidenotes.ui.theme.inverseSurfaceLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.inverseSurfaceLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onBackgroundDark
import com.visualrecursion.slidenotes.ui.theme.onBackgroundDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onBackgroundDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onBackgroundLight
import com.visualrecursion.slidenotes.ui.theme.onBackgroundLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onBackgroundLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onErrorContainerDark
import com.visualrecursion.slidenotes.ui.theme.onErrorContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onErrorContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onErrorContainerLight
import com.visualrecursion.slidenotes.ui.theme.onErrorContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onErrorContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onErrorDark
import com.visualrecursion.slidenotes.ui.theme.onErrorDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onErrorDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onErrorLight
import com.visualrecursion.slidenotes.ui.theme.onErrorLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onErrorLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onPrimaryContainerDark
import com.visualrecursion.slidenotes.ui.theme.onPrimaryContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onPrimaryContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onPrimaryContainerLight
import com.visualrecursion.slidenotes.ui.theme.onPrimaryContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onPrimaryContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onPrimaryDark
import com.visualrecursion.slidenotes.ui.theme.onPrimaryDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onPrimaryDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onPrimaryLight
import com.visualrecursion.slidenotes.ui.theme.onPrimaryLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onPrimaryLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onSecondaryContainerDark
import com.visualrecursion.slidenotes.ui.theme.onSecondaryContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onSecondaryContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onSecondaryContainerLight
import com.visualrecursion.slidenotes.ui.theme.onSecondaryContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onSecondaryContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onSecondaryDark
import com.visualrecursion.slidenotes.ui.theme.onSecondaryDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onSecondaryDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onSecondaryLight
import com.visualrecursion.slidenotes.ui.theme.onSecondaryLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onSecondaryLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onSurfaceDark
import com.visualrecursion.slidenotes.ui.theme.onSurfaceDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onSurfaceDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onSurfaceLight
import com.visualrecursion.slidenotes.ui.theme.onSurfaceLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onSurfaceLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onSurfaceVariantDark
import com.visualrecursion.slidenotes.ui.theme.onSurfaceVariantDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onSurfaceVariantDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onSurfaceVariantLight
import com.visualrecursion.slidenotes.ui.theme.onSurfaceVariantLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onSurfaceVariantLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onTertiaryContainerDark
import com.visualrecursion.slidenotes.ui.theme.onTertiaryContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onTertiaryContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onTertiaryContainerLight
import com.visualrecursion.slidenotes.ui.theme.onTertiaryContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onTertiaryContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onTertiaryDark
import com.visualrecursion.slidenotes.ui.theme.onTertiaryDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.onTertiaryDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.onTertiaryLight
import com.visualrecursion.slidenotes.ui.theme.onTertiaryLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.onTertiaryLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.outlineDark
import com.visualrecursion.slidenotes.ui.theme.outlineDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.outlineDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.outlineLight
import com.visualrecursion.slidenotes.ui.theme.outlineLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.outlineLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.outlineVariantDark
import com.visualrecursion.slidenotes.ui.theme.outlineVariantDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.outlineVariantDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.outlineVariantLight
import com.visualrecursion.slidenotes.ui.theme.outlineVariantLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.outlineVariantLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.primaryContainerDark
import com.visualrecursion.slidenotes.ui.theme.primaryContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.primaryContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.primaryContainerLight
import com.visualrecursion.slidenotes.ui.theme.primaryContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.primaryContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.primaryDark
import com.visualrecursion.slidenotes.ui.theme.primaryDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.primaryDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.primaryLight
import com.visualrecursion.slidenotes.ui.theme.primaryLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.primaryLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.scrimDark
import com.visualrecursion.slidenotes.ui.theme.scrimDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.scrimDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.scrimLight
import com.visualrecursion.slidenotes.ui.theme.scrimLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.scrimLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.secondaryContainerDark
import com.visualrecursion.slidenotes.ui.theme.secondaryContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.secondaryContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.secondaryContainerLight
import com.visualrecursion.slidenotes.ui.theme.secondaryContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.secondaryContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.secondaryDark
import com.visualrecursion.slidenotes.ui.theme.secondaryDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.secondaryDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.secondaryLight
import com.visualrecursion.slidenotes.ui.theme.secondaryLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.secondaryLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceBrightDark
import com.visualrecursion.slidenotes.ui.theme.surfaceBrightDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceBrightDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceBrightLight
import com.visualrecursion.slidenotes.ui.theme.surfaceBrightLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceBrightLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerDark
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighDark
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighLight
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighestDark
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighestDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighestLight
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighestLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerHighestLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLight
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowDark
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowLight
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowestDark
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowestDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowestLight
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowestLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceContainerLowestLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceDark
import com.visualrecursion.slidenotes.ui.theme.surfaceDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceDimDark
import com.visualrecursion.slidenotes.ui.theme.surfaceDimDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceDimDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceDimLight
import com.visualrecursion.slidenotes.ui.theme.surfaceDimLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceDimLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceLight
import com.visualrecursion.slidenotes.ui.theme.surfaceLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceVariantDark
import com.visualrecursion.slidenotes.ui.theme.surfaceVariantDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceVariantDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceVariantLight
import com.visualrecursion.slidenotes.ui.theme.surfaceVariantLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.surfaceVariantLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.tertiaryContainerDark
import com.visualrecursion.slidenotes.ui.theme.tertiaryContainerDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.tertiaryContainerDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.tertiaryContainerLight
import com.visualrecursion.slidenotes.ui.theme.tertiaryContainerLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.tertiaryContainerLightMediumContrast
import com.visualrecursion.slidenotes.ui.theme.tertiaryDark
import com.visualrecursion.slidenotes.ui.theme.tertiaryDarkHighContrast
import com.visualrecursion.slidenotes.ui.theme.tertiaryDarkMediumContrast
import com.visualrecursion.slidenotes.ui.theme.tertiaryLight
import com.visualrecursion.slidenotes.ui.theme.tertiaryLightHighContrast
import com.visualrecursion.slidenotes.ui.theme.tertiaryLightMediumContrast

@Immutable
data class ExtendedColorScheme(
    val customColor1: ColorFamily,
)

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun SlideNotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
