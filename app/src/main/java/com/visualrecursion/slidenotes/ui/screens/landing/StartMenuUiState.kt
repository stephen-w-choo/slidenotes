package com.visualrecursion.slidenotes.ui.screens.landing


sealed interface StartMenuUiState {
    data object Default : StartMenuUiState
    data object Loading : StartMenuUiState
    data class Error(
        val error: String
    ) : StartMenuUiState
    data class Parsed(
        val savedSlideNoteId: Long
    ) : StartMenuUiState
}