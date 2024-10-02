package com.visualrecursion.slidenotes.ui.screens.landing.startMenu

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.visualrecursion.slidenotes.domain.LoadPptxUseCase
import com.visualrecursion.slidenotes.ui.screens.landing.PptxObjectHolder
import com.visualrecursion.slidenotes.ui.utils.getFileDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StartMenuViewModel @Inject constructor(
    private val loadPptxUseCase: LoadPptxUseCase,
    private val pptxObjectHolder: PptxObjectHolder,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _startMenuUiState = MutableStateFlow<StartMenuUiState>(StartMenuUiState.Default)
    val startMenuUiState = _startMenuUiState.asStateFlow()

    private val _startMenuEvents = MutableSharedFlow<StartMenuEvent>()
    val startMenuEvents = _startMenuEvents.asSharedFlow()

    fun handleFileUri(uri: Uri?) {
        // TODO: Improve the logic here - would be easier to wrap the whole thing in a try block
        //  and catch in one place. Would remove duplication.
        _startMenuUiState.value = StartMenuUiState.Loading

        try {
            if (uri == null) throw Exception()

            CoroutineScope(Dispatchers.IO).launch {
                val xmlSlideShow = loadPptxUseCase(uri) ?: throw Exception()
                val fileDetails = getFileDetails(context, uri)

                pptxObjectHolder.pptxObject.value = PptxObject(fileDetails, xmlSlideShow)
                _startMenuEvents.emit(StartMenuEvent.PptxLoaded)
                resetState()
            }
        } catch (e: Exception) {
            setErrorState("We were unable to parse this PPTX file. Please try again, or check the file if it keeps happening.")
        }
    }

    fun checkValidFileExtension(fileExtension: String?): Boolean {
        if (fileExtension == "pptx") {
            return true
        }
        setErrorState("Invalid file type: $fileExtension. Only .pptx files are supported at the moment.")
        return false
    }

    private fun resetState() {
        _startMenuUiState.value = StartMenuUiState.Default
    }

    private fun setErrorState(error: String) {
        _startMenuUiState.value = StartMenuUiState.Error(
            error = error
        )
    }
}