package com.visualrecursion.slidenotes.ui.screens.notesView

import com.visualrecursion.slidenotes.domain.models.SlideNote

sealed interface NotesViewUiState {
    data object Loading : NotesViewUiState
    data object Error : NotesViewUiState
    data class Success(
        val notes: SlideNote
    ) : NotesViewUiState
}