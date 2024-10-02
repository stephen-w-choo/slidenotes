package com.visualrecursion.slidenotes.ui.screens.landing.conversionMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.ui.components.containers.PreviewContainer
import com.visualrecursion.slidenotes.ui.components.containers.VerticalScrollContainer
import com.visualrecursion.slidenotes.ui.navigation.components.CtaButton
import com.visualrecursion.slidenotes.ui.screens.landing.startMenu.FileDetails
import com.visualrecursion.slidenotes.ui.utils.convertBytes

@Composable
fun ConversionMenuView(
    viewModel: ConversionMenuViewModel,
    navigateToNotesView: (Long) -> Unit
) {
    val uiState = viewModel.conversionMenuUiState.collectAsState().value
    val pptxObjectState = viewModel.pptxObjectState

    VerticalScrollContainer {
        // handling state
        if (pptxObjectState == null) {
                // Show error with option to go back}
        } else {
            when (uiState) {
                is ConversionMenuUiState.Default -> {}
                is ConversionMenuUiState.Error -> {} // show the error dialog
                is ConversionMenuUiState.Parsed -> {
                    navigateToNotesView(uiState.savedSlideNoteId)
                    viewModel.resetViewModelState()
                }
            }

            ConversionMenu(
                fileDetails = pptxObjectState.fileDetails,
                convertWithSpeakerNotes = viewModel::convertWithSpeakerNotes
            )
        }
    }
}

@Composable
fun ConversionMenu(
    fileDetails: FileDetails,
    convertWithSpeakerNotes: () -> Unit
) {
    Column {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_ppt_file),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp),
                        tint = colorResource(id = R.color.dark_orange)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "Powerpoint file loaded",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))

                FileDetailsRow(
                    label = stringResource(R.string.name),
                    value = fileDetails.name ?: stringResource(R.string.name_fallback)
                )

                FileDetailsRow(
                    label = stringResource(R.string.size),
                    value = fileDetails.size.convertBytes() ?: stringResource(R.string.size_fallback)
                )
            }
        }

        CtaButton(
            onClick = convertWithSpeakerNotes,
            text = stringResource(R.string.generate_speaker_notes),
            rightIcon = R.drawable.ic_arrow_right,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        )

        CtaButton(
            onClick = { }, // TODO
            text = stringResource(R.string.generate_blank_note_per_slide),
            rightIcon = R.drawable.ic_arrow_right,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        )

        CtaButton(
            onClick = {  },
            text = stringResource(R.string.use_ai_to_generate_notes),
            enabled = false,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        )
    }
}

@Composable
fun FileDetailsRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
        ) {
            Text(
                text = label,
                maxLines = 1
            )
        }
        Text(
            text = value,
            modifier = Modifier.weight(3f),
        )
    }
}

@Preview
@Composable
fun ConversionMenuPreview() {
    PreviewContainer {
        ConversionMenu(
            FileDetails(
                name = "Test PPTrewqqqqqqqqqqqqqqgtg",
                size = 500
            ),
            {}
        )
    }
}

@Preview
@Composable
fun ConversionMenuPreviewDark() {
    PreviewContainer(
        darkTheme = true,
    ) {
        ConversionMenu(
            FileDetails(
                name = "Test PPTrewqqqqqqqqqqqqqqgtg",
                size = 500
            ),
            {}
        )
    }
}