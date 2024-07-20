package com.visualrecursion.slidenotes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.visualrecursion.slidenotes.data.entities.NotesCollectionEntity
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity

@Database(entities = [NotesCollectionEntity::class, SlideNoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun slideNoteDao(): SlideNotesDao
}