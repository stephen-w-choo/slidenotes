package com.visualrecursion.slidenotes.data

import android.content.Context
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import com.visualrecursion.slidenotes.data.entities.SlideNoteItemEntity
import com.visualrecursion.slidenotes.data.entities.toSlideNote
import com.visualrecursion.slidenotes.domain.models.SlideNote
import com.visualrecursion.slidenotes.ui.DeletionFailedException
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SlideNotesRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val db = AppDatabase.getDatabase(context)

    val slideNotesDao = db.slideNoteDao()

    // Group functions
    suspend fun saveSlideNote(slideNote: SlideNote): Long {
        val collectionId = slideNotesDao.insertSlideNote(
            SlideNoteEntity(name = slideNote.name)
        )

        val newSlideNoteEntities = slideNote.notes.mapIndexed { index, it ->
            SlideNoteItemEntity(
                header = it.header,
                content = it.content,
                index = index,
                collectionId = collectionId
            )
        }

        slideNotesDao.insertSlideNoteItems(newSlideNoteEntities)

        return collectionId
    }

    fun getSlideNote(slideNoteId: Long): Flow<SlideNote?> {
        return combine(
            slideNotesDao.getSlideNoteById(slideNoteId),
            slideNotesDao.getSlideItemListById(slideNoteId)
        ) { slideNote, slideNoteItems ->
            try {
                SlideNote(
                    name = slideNote.name,
                    notes = slideNoteItems.map {
                        it.toSlideNote()
                    }
                )
            } catch(e: Exception) {
                // Edge case - if we're currently reading the slide, but it gets deleted somehow, return null
                null
            }
        }
    }

    // Slide note collection functions
    suspend fun updateSlideNoteCollection(id: Long, name: String) {
        slideNotesDao.updateSlideNote(id, name)
    }

    suspend fun deleteSlideNoteCollection(slideNoteId: Long) {
        val res = slideNotesDao.deleteNotesCollection(slideNoteId)
        if (res == 0) throw DeletionFailedException()
    }

    fun getAllSlideNoteEntitiesFlow(): Flow<List<SlideNoteEntity>> {
        val res = slideNotesDao.getAllSlideNoteEntities().map {
            it
        }
        return res
    }

    // Slide note individual item functions
}