package com.visualrecursion.slidenotes.ui.screens.notesView

import com.visualrecursion.slidenotes.domain.models.NotesCollection

sealed interface NotesViewUiState {
    data object Loading : NotesViewUiState
    data object Error : NotesViewUiState
    data class Success(
        val notes: NotesCollection
    ) : NotesViewUiState
}