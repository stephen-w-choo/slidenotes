package com.visualrecursion.slidenotes.ui.screens.landing.startMenu

import org.apache.poi.xslf.usermodel.XMLSlideShow


sealed interface StartMenuUiState {
    data object Default : StartMenuUiState
    data object Loading : StartMenuUiState
    data class Error(
        val error: String
    ) : StartMenuUiState

    data object PptxLoaded : StartMenuUiState
}

data class PptxObject(
    val fileDetails: FileDetails,
    val xmlSlideShow: XMLSlideShow
)

data class FileDetails(
    val name: String?,
    val size: Long?
)