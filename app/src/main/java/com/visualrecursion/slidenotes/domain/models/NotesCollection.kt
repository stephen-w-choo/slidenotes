package com.visualrecursion.slidenotes.domain.models

data class NotesCollection(
    val name: String,
    val notes: List<SlideNote>
)
