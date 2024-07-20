package com.visualrecursion.slidenotes.ui.screens.landing

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.visualrecursion.slidenotes.domain.ConvertPptUseCase
import com.visualrecursion.slidenotes.domain.LoadPptxUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartMenuViewModel @Inject constructor(
    private val convertPptUseCase: ConvertPptUseCase,
    private val loadPptxUseCase: LoadPptxUseCase
): ViewModel() {
    private val _startMenuUiState = MutableStateFlow<StartMenuUiState>(StartMenuUiState.Default)
    val startMenuUiState = _startMenuUiState.asStateFlow()

    fun handleFileUri(uri: Uri?) {
        // TODO: Improve the logic here - would be easier to wrap the whole thing in a try block
        //  and catch in one place. Would remove duplication.
        _startMenuUiState.value = StartMenuUiState.Loading

        if (uri == null) { setErrorState("File appears to be empty or blank") }

        if (uri != null) {
            CoroutineScope(Dispatchers.IO).launch {
                val pptxObject = loadPptxUseCase(uri)
                if (pptxObject == null) {
                    setErrorState("File appears to be empty or blank")
                    return@launch
                }

                try {
                    val result = convertPptUseCase("Placeholder", pptxObject)
                    _startMenuUiState.value = StartMenuUiState.Parsed(
                        savedSlideNoteId = result
                    )
                } catch(e: Exception) {
                    setErrorState("Error parsing file: $e")
                }
            }
        }
    }

    fun checkValidFileExtension(fileExtension: String?): Boolean {
        if (fileExtension == "pptx") {
            return true
        }
        setErrorState("Invalid file type: $fileExtension. Only .pptx files are supported at the moment.")
        return false
    }

    fun resetState() {
        _startMenuUiState.value = StartMenuUiState.Default
    }

    private fun setErrorState(error: String) {
        _startMenuUiState.value = StartMenuUiState.Error(
            error = error
        )
    }
}