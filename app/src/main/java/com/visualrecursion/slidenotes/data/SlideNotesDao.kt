package com.visualrecursion.slidenotes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import com.visualrecursion.slidenotes.data.entities.NotesCollectionEntity

@Dao
interface SlideNotesDao {
    @Insert
    suspend fun insertSlideNoteCollection(notesCollectionEntity: NotesCollectionEntity): Long

    @Insert
    suspend fun insertSlideNotes(slideNoteEntities: List<SlideNoteEntity>): List<Long>

    @Query("SELECT * FROM slide_note_collections WHERE id == :id")
    suspend fun getNotesCollection(id: Long): NotesCollectionEntity

    @Query("SELECT * FROM slide_notes WHERE collectionId == :collectionId ORDER BY `index`")
    suspend fun getSlideNotes(collectionId: Long): List<SlideNoteEntity>

    @Query("SELECT * FROM slide_note_collections")
    suspend fun getAllCollectionEntities(): List<NotesCollectionEntity>
}