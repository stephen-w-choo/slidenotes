package com.visualrecursion.slidenotes.domain.models

data class SlideNote(
    val name: String,
    val notes: List<SlideNoteItem>
)
