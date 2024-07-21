package com.visualrecursion.slidenotes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import com.visualrecursion.slidenotes.data.entities.NotesCollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SlideNotesDao {
    @Insert
    suspend fun insertSlideNoteCollection(notesCollectionEntity: NotesCollectionEntity): Long

    @Insert
    suspend fun insertSlideNotes(slideNoteEntities: List<SlideNoteEntity>): List<Long>

    @Query("SELECT * FROM slide_note_collections WHERE id == :id")
    fun getNotesCollection(id: Long): Flow<NotesCollectionEntity>

    @Query("SELECT * FROM slide_notes WHERE collectionId == :collectionId ORDER BY `index`")
    fun getSlideNotes(collectionId: Long): Flow<List<SlideNoteEntity>>

    @Query("SELECT * FROM slide_note_collections")
    fun getAllCollectionEntities(): Flow<List<NotesCollectionEntity>>
}