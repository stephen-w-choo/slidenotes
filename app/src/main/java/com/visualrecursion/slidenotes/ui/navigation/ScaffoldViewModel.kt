package com.visualrecursion.slidenotes.ui.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import com.visualrecursion.slidenotes.domain.RepositoryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    private val repositoryUseCases: RepositoryUseCases
): ViewModel() {

    private val _navDrawerUiState = MutableStateFlow<List<SlideNoteEntity>>(emptyList())
    val navDrawerUiState = _navDrawerUiState.asStateFlow()

    init {
        startEntitiesFlowCollection()
    }

    private fun startEntitiesFlowCollection() = viewModelScope.launch(Dispatchers.IO) {
        repositoryUseCases.getAllCollectionEntitiesFlow().collect {
            _navDrawerUiState.value = it
        }
    }

    fun deleteEntityById(id: Long) {
        try {
            viewModelScope.launch {
                repositoryUseCases.deleteNotesCollectionById(id)
            }
        } catch (e: Exception) {
            // TODO - add a toast
        }
    }
}