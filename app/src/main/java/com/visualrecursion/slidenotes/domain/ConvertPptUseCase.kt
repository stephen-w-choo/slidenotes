package com.visualrecursion.slidenotes.domain

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFNotes
import org.apache.poi.xslf.usermodel.XSLFSlide
import java.io.InputStream
import javax.inject.Inject

class ConvertPptUseCase @Inject constructor(
    @ApplicationContext val appContext: Context
) {
    suspend operator fun invoke(uri: Uri): List<SlideData> {
        return withContext(Dispatchers.IO) { // Make sure we're running on IO
            val contentResolver = appContext.contentResolver
            val inputStream = contentResolver.openInputStream(uri)

            if (inputStream != null) {
                parseInputStream(inputStream) // implicit return
            } else {
                listOf() // implicit return
            }
        }
    }

    private fun parseInputStream(inputStream: InputStream): List<SlideData> {
        inputStream.use { stream ->
            val pptObject = XMLSlideShow(stream)
            val slides = pptObject.slides
            val res = mutableListOf<SlideData>()

            for (slide in slides) {
                val slideTitle = slide.title ?: ""
                val speakerNotes = extractSpeakerNotesFromSlides(slide)

                val slideNote = SlideData(slideTitle, speakerNotes)

                res.add(slideNote)
            }
            return res
        }
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