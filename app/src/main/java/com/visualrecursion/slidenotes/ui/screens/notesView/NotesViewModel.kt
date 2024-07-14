package com.visualrecursion.slidenotes.ui.screens.notesView

import androidx.lifecycle.ViewModel
import com.visualrecursion.slidenotes.domain.SlideData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(

): ViewModel() {
    private val _result = MutableStateFlow<List<SlideData>>(emptyList())
    val result = _result.asStateFlow()

    fun setSlideData(slideData: List<SlideData>) {
        _result.value = slideData
    }
}