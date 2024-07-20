package com.visualrecursion.slidenotes.domain

import android.content.Context
import com.visualrecursion.slidenotes.data.SlideNotesRepository
import com.visualrecursion.slidenotes.domain.models.SlideNote
import com.visualrecursion.slidenotes.domain.models.NotesCollection
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFNotes
import org.apache.poi.xslf.usermodel.XSLFSlide
import javax.inject.Inject


class ConvertPptUseCase @Inject constructor(
    private val slideNotesRepository: SlideNotesRepository
) {
    suspend operator fun invoke(name: String, pptxObject: XMLSlideShow): Long {
        return withContext(Dispatchers.IO) { // Make sure we're running on IO
            val slideNotes = parsePptx(pptxObject)
            slideNotesRepository.saveSlideNoteCollection( // implicit return of ID
                NotesCollection(
                    name = name,
                    notes = slideNotes
                )
            )
        }
    }

    private fun parsePptx(pptxObject: XMLSlideShow): List<SlideNote> {
        val slides = pptxObject.slides
        val res = mutableListOf<SlideNote>()

        for (slide in slides) {
            val slideTitle = slide.title ?: ""
            val speakerNotes = extractSpeakerNotesFromSlides(slide)
            val slideNote = SlideNote(slideTitle, speakerNotes)
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