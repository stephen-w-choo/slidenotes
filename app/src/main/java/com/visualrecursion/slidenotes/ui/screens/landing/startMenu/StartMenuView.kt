package com.visualrecursion.slidenotes.ui.screens.landing.startMenu

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.ui.components.ErrorBox
import com.visualrecursion.slidenotes.ui.components.LoadingIndicator
import com.visualrecursion.slidenotes.ui.utils.getFileExtension


@Composable
fun StartMenuView(
    viewModel: StartMenuViewModel,
    navigateToConversionMenu: () -> Unit
) {
    val uiState = viewModel.startMenuUiState.collectAsState().value

    if (uiState is StartMenuUiState.PptxLoaded) {
        navigateToConversionMenu()
        viewModel.resetState()
    }

    Column {
        LoadDocumentButton(
            viewModel = viewModel,
            disabled = (uiState is StartMenuUiState.Loading
                     || uiState is StartMenuUiState.PptxLoaded)
        )
        Column(
            modifier = Modifier.heightIn(min = 300.dp)
        ) {
            AnimatedVisibility(visible = uiState is StartMenuUiState.Loading) {
                LoadingIndicator(stringResource(R.string.loading_text_processing))
            }
            AnimatedVisibility(visible = uiState is StartMenuUiState.Error) {
                if (uiState is StartMenuUiState.Error) {
                    ErrorBox(errorMessage = uiState.error)
                }
            }
        }
    }
}

@Composable
fun LoadDocumentButton(
    viewModel: StartMenuViewModel,
    disabled: Boolean
) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri: Uri? ->
            if (uri == null) { // if the user doesn't choose a file
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

    Button(
        onClick = { launcher.launch(arrayOf("*/*")) },
        enabled = !disabled
    ) {
        Text(text = "Create a note")
    }
}
