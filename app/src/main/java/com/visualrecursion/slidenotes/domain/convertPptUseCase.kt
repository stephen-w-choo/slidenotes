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
    suspend operator fun invoke(uri: Uri): List<String> {
        return withContext(Dispatchers.IO) { // Make sure we're running on IO
            val contentResolver = appContext.contentResolver
            val inputStream = contentResolver.openInputStream(uri)

            if (inputStream != null) {
                parseInputStream(inputStream) // implicit return
            } else {
                listOf("") // implicit return
            }
        }
    }

    private fun parseInputStream(inputStream: InputStream): List<String> {
        inputStream.use { stream ->
            val pptObject = XMLSlideShow(stream)
            val slides = pptObject.slides
            return extractSpeakerNotesFromSlides(slides)
        }
    }

    private fun extractSpeakerNotesFromSlides(slides: List<XSLFSlide>): List<String> {
        val res = mutableListOf<String>()

        for (slide in slides) {
            val notes: XSLFNotes? = slide.notes
            if (notes != null) {
                val notesParagraphs = notes.textParagraphs
                val notesAsText = notesParagraphs.flatten().map{ it.text }
                val joinedNotesAsText = notesAsText.joinToString("\n")
                res.add(joinedNotesAsText)
            }
        }

        return res
    }
}