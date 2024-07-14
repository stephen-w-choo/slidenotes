package com.visualrecursion.slidenotes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.compose.SlideNotesTheme

data class DynamicUiValues(
    val fontScale: Int = 8,
    val useDarkMode: Boolean = false
)

val DynamicUiValuesProvider = compositionLocalOf { DynamicUiValues() }

const val MIN_FONT_SIZE = 10
const val MAX_FONT_SIZE = 36

val FontSizes = (MIN_FONT_SIZE..MAX_FONT_SIZE).toList()

data class ThemeActions(
    val switchTheme: () -> Unit,
    val changeFontSize: (Int) -> Unit
)

@Composable
fun ThemeWrapper(
    children: @Composable (ThemeActions) -> Unit
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    var dynamicUiValues by remember {
        mutableStateOf(
            DynamicUiValues(useDarkMode = isSystemInDarkTheme)
        )
    }

    fun switchTheme() {
        dynamicUiValues = dynamicUiValues.copy(
            useDarkMode = !dynamicUiValues.useDarkMode
        )
    }

    fun changeFontSize(index: Int) {
        // `index` value must be restricted to fontSizes.size.
        // Unsure how to enforce this at a type level.
        dynamicUiValues = dynamicUiValues.copy(
            fontScale = index
        )
    }

    SlideNotesTheme(darkTheme = dynamicUiValues.useDarkMode) {
        CompositionLocalProvider(DynamicUiValuesProvider provides dynamicUiValues) {
            children(
                ThemeActions(
                    switchTheme = { switchTheme() },
                    changeFontSize = { changeFontSize(it) }
                )
            )
        }
    }
}