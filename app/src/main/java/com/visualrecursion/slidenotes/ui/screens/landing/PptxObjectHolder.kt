package com.visualrecursion.slidenotes.ui.screens.landing

import androidx.compose.runtime.mutableStateOf
import com.visualrecursion.slidenotes.ui.screens.landing.startMenu.PptxObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PptxObjectHolder @Inject constructor() {
    var pptxObject = mutableStateOf<PptxObject?>(null)
}