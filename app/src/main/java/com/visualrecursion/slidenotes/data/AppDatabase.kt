package com.visualrecursion.slidenotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity
import com.visualrecursion.slidenotes.data.entities.SlideNoteItemEntity

@Database(entities = [SlideNoteEntity::class, SlideNoteItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                // if the Instance is not null, return it, otherwise create a new database instance.
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "slide_notes_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

    abstract fun slideNoteDao(): SlideNotesDao
}