package com.visualrecursion.slidenotes.domain

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFNotes
import org.apache.poi.xslf.usermodel.XSLFSlide
import javax.inject.Inject

// Technically a pure function that could be a util, but I've moved it into a use case as
// it involves a potentially CPU intensive process, which needs to be launched in a coroutine.
// Also, it will help if I ever need dependency injection.

class ConvertPptUseCase @Inject constructor(
    @ApplicationContext val appContext: Context
) {
    suspend operator fun invoke(pptxObject: XMLSlideShow): List<SlideData> {
        return withContext(Dispatchers.IO) { // Make sure we're running on IO
            parsePptx(pptxObject)
        }
    }

    private fun parsePptx(pptxObject: XMLSlideShow): List<SlideData> {
        val slides = pptxObject.slides
        val res = mutableListOf<SlideData>()

        for (slide in slides) {
            val slideTitle = slide.title ?: ""
            val speakerNotes = extractSpeakerNotesFromSlides(slide)
            val slideNote = SlideData(slideTitle, speakerNotes)
            res.add(slideNote)
        }
        return res
    }

    private fun extractSpeakerNotesFromSlides(slide: XSLFSlide): String {
        val notes: XSLFNotes? = slide.notes
        if (notes != null) {
            val notesParagraphs = notes.textParagraphs
            val notesAsText = notesParagraphs.flatten().map{ it.text }
            return notesAsText.joinToString("\n")
        }
        return ""
    }
}