package com.visualrecursion.slidenotes.domain

import com.visualrecursion.slidenotes.data.SlideNotesRepository
import com.visualrecursion.slidenotes.domain.models.SlideNote
import com.visualrecursion.slidenotes.domain.models.SlideNoteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFNotes
import org.apache.poi.xslf.usermodel.XSLFSlide
import javax.inject.Inject


class ConvertPptUseCase @Inject constructor(
    private val slideNotesRepository: SlideNotesRepository
) {
    /**
     Converts the PPT, loads into the DB, and returns the slideNote ID for DB retrieval
     */
    suspend operator fun invoke(name: String, pptxObject: XMLSlideShow): Long {
        return withContext(Dispatchers.IO) { // Make sure we're running on IO
            val slideNotes = parsePptx(pptxObject)
            slideNotesRepository.saveSlideNote( // implicit return of ID
                SlideNote(
                    name = name,
                    notes = slideNotes
                )
            )
        }
    }

    private fun parsePptx(pptxObject: XMLSlideShow): List<SlideNoteItem> {
        val slides = pptxObject.slides
        val res = mutableListOf<SlideNoteItem>()

        for (slide in slides) {
            val slideTitle = slide.title ?: ""
            val speakerNotes = extractSpeakerNotesFromSlides(slide)
            val slideNoteItem = SlideNoteItem(slideTitle, speakerNotes)
            res.add(slideNoteItem)
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