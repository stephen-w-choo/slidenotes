package com.visualrecursion.slidenotes.domain

import com.visualrecursion.slidenotes.data.SlideNotesRepository
import com.visualrecursion.slidenotes.data.entities.NotesCollectionEntity
import com.visualrecursion.slidenotes.domain.models.NotesCollection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryUseCases @Inject constructor(
    private val slideNotesRepository: SlideNotesRepository
) {
    fun getNotesCollectionById(id: Long): Flow<NotesCollection> {
        return slideNotesRepository.getNotesCollection(id)
    }

    fun getAllCollectionEntitiesFlow(): Flow<List<NotesCollectionEntity>> {
        return slideNotesRepository.getAllNotesCollectionEntitiesFlow()
    }
}