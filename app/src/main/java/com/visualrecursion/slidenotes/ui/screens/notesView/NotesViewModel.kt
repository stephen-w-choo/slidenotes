package com.visualrecursion.slidenotes.ui.screens.notesView

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualrecursion.slidenotes.domain.RepositoryUseCases
import com.visualrecursion.slidenotes.ui.navigation.NavRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repositoryUseCases: RepositoryUseCases
): ViewModel() {

    private val _uiState = MutableStateFlow<NotesViewUiState>(NotesViewUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        val collectionId: Long? = savedStateHandle[NavRoute.Arguments.COLLECTION_ID]
        fetchNotes(collectionId)
    }

    private fun fetchNotes(collectionId: Long?) {
        try {
            if (collectionId == null) throw Exception()
            viewModelScope.launch {
                repositoryUseCases.getNotesCollectionById(collectionId).collect {
                    if (it == null) {
                        _uiState.value = NotesViewUiState.Error
                    } else {
                        _uiState.value = NotesViewUiState.Success(it)
                    }
                }
            }
        } catch (e: Exception) {
            _uiState.value = NotesViewUiState.Error
        }
    }
}