package com.visualrecursion.slidenotes.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.visualrecursion.slidenotes.domain.models.SlideNoteItem

@Entity(
    tableName = "slide_notes",
    foreignKeys = [
        ForeignKey(
            entity = SlideNoteEntity::class,
            parentColumns = ["id"],
            childColumns = ["collectionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["collectionId"])]
)
data class SlideNoteItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val header: String,
    val content: String,
    val index: Int,
    val collectionId: Long
)

fun SlideNoteItemEntity.toSlideNote(): SlideNoteItem {
    return SlideNoteItem(
        this.header,
        this.content
    )
}

