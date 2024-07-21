package com.visualrecursion.slidenotes.ui.screens.notesView

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.domain.models.SlideNote
import com.visualrecursion.slidenotes.ui.components.NotesPager

@Composable
fun NotesView(
    viewModel: NotesViewModel,
    navigateBackToStart: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    when (uiState) {
        is NotesViewUiState.Loading -> {} // Do nothing
        is NotesViewUiState.Error -> {
            navigateBackToStart()
            Toast.makeText(
                context,
                stringResource(R.string.navigation_error_message),
                Toast.LENGTH_SHORT
            ).show()
        }
        is NotesViewUiState.Success -> {
            NotesSuccessView(uiState.notes)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesSuccessView(
    slideNote: SlideNote
) {

    val pagerState = rememberPagerState { slideNote.notes.size }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        NotesPager(
            notes = slideNote.notes.map { it.content },
            pagerState = pagerState
        )
    }
}