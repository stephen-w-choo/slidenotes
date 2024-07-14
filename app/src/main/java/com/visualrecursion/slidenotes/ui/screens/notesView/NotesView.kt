package com.visualrecursion.slidenotes.ui.screens.notesView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.visualrecursion.slidenotes.ui.components.NotesPager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesView(
    viewModel: NotesViewModel
) {
    val notesAsState = viewModel.result.collectAsState().value
    val pagerState = rememberPagerState { notesAsState.size }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        NotesPager(
            notes = notesAsState.map { it.speakerNotes },
            pagerState = pagerState
        )
    }
}