package com.visualrecursion.slidenotes.domain

import com.visualrecursion.slidenotes.data.SlideNotesRepository
import com.visualrecursion.slidenotes.domain.models.NotesCollection
import javax.inject.Inject

class GetNotesCollectionById @Inject constructor(
    private val slideNotesRepository: SlideNotesRepository
) {
    suspend operator fun invoke(id: Long): NotesCollection {
        return slideNotesRepository.getNotesCollection(id)
    }
}