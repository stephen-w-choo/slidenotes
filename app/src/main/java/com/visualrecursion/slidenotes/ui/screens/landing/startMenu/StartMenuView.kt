package com.visualrecursion.slidenotes.ui.screens.landing.startMenu

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.ui.components.containers.PreviewContainer
import com.visualrecursion.slidenotes.ui.navigation.components.CtaButton
import com.visualrecursion.slidenotes.ui.utils.getFileExtension


@Composable
fun StartMenuView(
    viewModel: StartMenuViewModel,
    navigateToConversionMenu: () -> Unit
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {

    }

    LaunchedEffect(Unit) {
        viewModel.startMenuEvents.collect { event ->
            when (event) {
                is StartMenuEvent.PptxLoaded -> {
                    navigateToConversionMenu()
                }
            }
        }
    }

    val uiState = viewModel.startMenuUiState.collectAsState().value

    val context = LocalContext.current

    val documentSelectionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            if (uri == null) { // will be null if the user doesn't choose a file
                return@rememberLauncherForActivityResult
            }
            // Handle the selected file URI here
            val fileExtension = getFileExtension(context, uri)

            // check the file extension
            if (viewModel.checkValidFileExtension(fileExtension)) {
                viewModel.handleFileUri(uri)
            }
        }
    )
    val launchDocumentSelectionAction = { documentSelectionLauncher.launch(arrayOf("*/*")) }

    StartMenuScreen(
        launchDocumentPicker = launchDocumentSelectionAction,
        uiState = uiState
    )
}

@Composable
fun StartMenuScreen(
    launchDocumentPicker: () -> Unit,
    uiState: StartMenuUiState,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_slidenote_logo),
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        CtaButton(
            text = stringResource(R.string.make_note_slide_deck),
            subtext = stringResource(R.string.supported_file_types),
            leftIcon = R.drawable.ic_load_previous,
            onClick = launchDocumentPicker,
            enabled = uiState is StartMenuUiState.Default,
            loading = uiState is StartMenuUiState.Loading,
        )

        Spacer(modifier = Modifier.size(16.dp))

        CtaButton(
            text = stringResource(R.string.make_new_blank_note),
            leftIcon = R.drawable.ic_new_note,
            onClick = { }, // TODO
            enabled = uiState is StartMenuUiState.Default,
            loading = uiState is StartMenuUiState.Loading
        )

        Spacer(modifier = Modifier.size(16.dp))

        CtaButton(
            text = stringResource(R.string.view_previously_created_notes),
            leftIcon = R.drawable.ic_page_history,
            onClick = { }, // TODO
        )
    }
}


@Preview
@Composable
fun StartMenuScreenPreview() {
    PreviewContainer {
        StartMenuScreen(
            launchDocumentPicker = {},
            uiState = StartMenuUiState.Default
        )
    }
}


@Preview
@Composable
fun StartMenuScreenPreviewDark() {
    PreviewContainer(
        darkTheme = true
    ) {
        StartMenuScreen(
            launchDocumentPicker = {},
            uiState = StartMenuUiState.Default
        )
    }
}

@Preview
@Composable
fun StartMenuScreenPreviewLoading() {
    PreviewContainer {
        StartMenuScreen(
            launchDocumentPicker = {},
            uiState = StartMenuUiState.Loading
        )
    }
}

@Preview
@Composable
fun StartMenuScreenPreviewError() {
    PreviewContainer {
        StartMenuScreen(
            launchDocumentPicker = {},
            uiState = StartMenuUiState.Error(error = "Failed to load document")
        )
    }
}