package com.visualrecursion.slidenotes.data

import android.content.Context
import androidx.room.Room
import com.visualrecursion.slidenotes.data.entities.NotesCollectionEntity
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import com.visualrecursion.slidenotes.data.entities.toSlideNote
import com.visualrecursion.slidenotes.domain.models.NotesCollection
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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

    fun getNotesCollection(collectionId: Long): Flow<NotesCollection> {
        return combine(
            slideNotesDao.getNotesCollection(collectionId),
            slideNotesDao.getSlideNotes(collectionId)
        ) { notesCollection, slideNotesList ->
            NotesCollection(
                name = notesCollection.name,
                notes = slideNotesList.map {
                    it.toSlideNote()
                }
            )
        }
    }

    fun getAllNotesCollectionEntitiesFlow(): Flow<List<NotesCollectionEntity>> {
        return slideNotesDao.getAllCollectionEntities()
    }
}