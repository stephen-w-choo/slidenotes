package com.visualrecursion.slidenotes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.visualrecursion.slidenotes.data.entities.SlideNoteItemEntity
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SlideNotesDao {
    @Insert
    suspend fun insertSlideNote(slideNoteEntity: SlideNoteEntity): Long

    @Insert
    suspend fun insertSlideNoteItems(slideNoteEntities: List<SlideNoteItemEntity>): List<Long>

    @Query("DELETE FROM slide_note_collections WHERE id == :id")
    suspend fun deleteNotesCollection(id: Long): Int

    @Query("SELECT * FROM slide_note_collections WHERE id == :id")
    fun getSlideNoteById(id: Long): Flow<SlideNoteEntity>

    @Query("SELECT * FROM slide_notes WHERE collectionId == :collectionId ORDER BY `index`")
    fun getSlideItemListById(collectionId: Long): Flow<List<SlideNoteItemEntity>>

    @Query("SELECT * FROM slide_note_collections")
    fun getAllSlideNoteEntities(): Flow<List<SlideNoteEntity>>

    @Query("UPDATE slide_note_collections SET name = :name WHERE id == :id")
    suspend fun updateSlideNote(id: Long, name: String)
}