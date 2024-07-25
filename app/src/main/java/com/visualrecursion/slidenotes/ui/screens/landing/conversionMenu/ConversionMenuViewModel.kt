package com.visualrecursion.slidenotes.ui.screens.landing.conversionMenu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualrecursion.slidenotes.domain.ConversionOption
import com.visualrecursion.slidenotes.domain.ConvertPptUseCase
import com.visualrecursion.slidenotes.ui.screens.landing.PptxObjectHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversionMenuViewModel @Inject constructor(
    val convertPptUseCase: ConvertPptUseCase,
    pptxObjectHolder: PptxObjectHolder
): ViewModel() {
    var newSlideNoteName by mutableStateOf("New SlideNote")
    var pptxObjectState by pptxObjectHolder.pptxObject

    private val _conversionMenuUiState = MutableStateFlow<ConversionMenuUiState>(ConversionMenuUiState.Default)
    val conversionMenuUiState = _conversionMenuUiState.asStateFlow()

    fun convertWithSpeakerNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pptxObject = pptxObjectState ?: throw Exception()

                val resultCollectionId = convertPptUseCase(
                    newSlideNoteName,
                    pptxObject.xmlSlideShow,
                    ConversionOption.SpeakerNotes
                )

                _conversionMenuUiState.value = ConversionMenuUiState.Parsed(resultCollectionId)
            } catch(e: Exception) {
//                setErrorState("Error parsing file: $e") TODO
            }
        }
    }

    fun resetViewModelState() {
        _conversionMenuUiState.value = ConversionMenuUiState.Default
    }
}