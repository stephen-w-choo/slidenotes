package org.visualrecursion.slidenotes.view

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.visualrecursion.slidenotes.ui.view.components.NotesPager

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileConversionView(
    viewModel: FileConversionViewModel,
    modifier: Modifier
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            // Handle the selected file URI here
            viewModel.handleFileUri(uri)
        }
    )

    val notesAsState = viewModel.result.collectAsState().value
    val pagerState = rememberPagerState { notesAsState.size }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { launcher.launch(arrayOf("*/*")) }) {
            Text(text = "Test")
        }
        NotesPager(
            notes = notesAsState.map { it.speakerNotes },
            pagerState = pagerState
        )
    }
}