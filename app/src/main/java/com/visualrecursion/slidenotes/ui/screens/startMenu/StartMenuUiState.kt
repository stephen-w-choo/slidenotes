package com.visualrecursion.slidenotes.ui.screens.startMenu

import com.visualrecursion.slidenotes.domain.SlideData

sealed interface StartMenuUiState {
    data object Default : StartMenuUiState
    data object Loading : StartMenuUiState
    data class Error(
        val error: String
    ) : StartMenuUiState
    data class Parsed(
        val parsedObject: List<SlideData>
    ) : StartMenuUiState
}