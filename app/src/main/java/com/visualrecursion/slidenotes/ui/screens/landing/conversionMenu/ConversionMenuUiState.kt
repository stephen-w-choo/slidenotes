package com.visualrecursion.slidenotes.ui.screens.landing.conversionMenu

sealed interface ConversionMenuUiState {
    data object Default : ConversionMenuUiState
    data class Error(
        val error: String
    ) : ConversionMenuUiState
    data class Parsed(
        val savedSlideNoteId: Long
    ) : ConversionMenuUiState
}
