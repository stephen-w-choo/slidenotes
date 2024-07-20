package com.visualrecursion.slidenotes.ui.screens.notesView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualrecursion.slidenotes.domain.GetNotesCollectionById
import com.visualrecursion.slidenotes.ui.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val getNotesCollectionById: GetNotesCollectionById
): ViewModel() {
    val collectionId: Long? = savedStateHandle[NavRoute.Arguments.COLLECTION_ID]

    private val _uiState = MutableStateFlow<NotesViewUiState>(NotesViewUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchNotes(collectionId)
    }

    private fun fetchNotes(collectionId: Long?) {
        try {
            if (collectionId == null) throw Exception()

            viewModelScope.launch {
                val notes = getNotesCollectionById(collectionId)
                _uiState.value = NotesViewUiState.Success(notes)
            }
        } catch (e: Exception) {
            _uiState.value = NotesViewUiState.Error
        }
    }
}