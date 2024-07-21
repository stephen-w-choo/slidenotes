package com.visualrecursion.slidenotes.domain

import com.visualrecursion.slidenotes.data.SlideNotesRepository
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import com.visualrecursion.slidenotes.domain.models.SlideNote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryUseCases @Inject constructor(
    private val slideNotesRepository: SlideNotesRepository
) {
    fun getNotesCollectionById(id: Long): Flow<SlideNote?> {
        return slideNotesRepository.getSlideNote(id)
    }

    suspend fun deleteNotesCollectionById(id: Long) {
        slideNotesRepository.deleteSlideNote(id)
    }

    fun getAllCollectionEntitiesFlow(): Flow<List<SlideNoteEntity>> {
        return slideNotesRepository.getAllSlideNoteEntitiesFlow()
    }
}