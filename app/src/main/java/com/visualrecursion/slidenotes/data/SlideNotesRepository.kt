package com.visualrecursion.slidenotes.data

import android.content.Context
import androidx.room.Room
import com.visualrecursion.slidenotes.data.entities.NotesCollectionEntity
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import com.visualrecursion.slidenotes.data.entities.toSlideNote
import com.visualrecursion.slidenotes.domain.models.NotesCollection
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SlideNotesRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "slide_notes_database"
    ).build()

    val slideNotesDao = db.slideNoteDao()

    suspend fun saveSlideNoteCollection(notesCollection: NotesCollection): Long {
        val collectionId = slideNotesDao.insertSlideNoteCollection(
            NotesCollectionEntity(name = notesCollection.name)
        )

        val newSlideNoteEntities = notesCollection.notes.mapIndexed { index, it ->
            SlideNoteEntity(
                header = it.header,
                content = it.content,
                index = index,
                collectionId = collectionId
            )
        }

        slideNotesDao.insertSlideNotes(newSlideNoteEntities)

        return collectionId
    }

    suspend fun getNotesCollection(collectionId: Long): NotesCollection {
        return NotesCollection(
            name = slideNotesDao.getNotesCollection(collectionId).name,
            notes = slideNotesDao.getSlideNotes(collectionId).map {
                it.toSlideNote()
            }
        )
    }

    suspend fun getAllNotesCollectionEntities(): List<NotesCollectionEntity> {
        return slideNotesDao.getAllCollectionEntities()
    }
}