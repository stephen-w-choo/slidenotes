package com.visualrecursion.slidenotes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose.SlideNotesTheme

data class DynamicUiValues(
    val fontSize: FontSize = FontSize.SMALL,
    val useDarkMode: Boolean = false
)

val DynamicUiValuesProvider = compositionLocalOf { DynamicUiValues() }

enum class FontSize(val value: Dp) {
    SMALLER(10.dp),
    SMALL(14.dp),
    MEDIUM(18.dp),
    LARGE(22.dp),
    LARGER(26.dp)
}

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
            fontSize = FontSize.entries[index]
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