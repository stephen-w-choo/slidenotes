package com.visualrecursion.slidenotes.domain

import com.visualrecursion.slidenotes.data.SlideNotesRepository
import com.visualrecursion.slidenotes.domain.models.SlideNote
import com.visualrecursion.slidenotes.domain.models.SlideNoteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.xslf.usermodel.XMLSlideShow
import org.apache.poi.xslf.usermodel.XSLFNotes
import org.apache.poi.xslf.usermodel.XSLFSlide
import org.apache.poi.xslf.usermodel.XSLFTextShape
import javax.inject.Inject

enum class ConversionOption {
    SpeakerNotes,
    SlideContent,
    AiGeneration
}

class ConvertPptUseCase @Inject constructor(
    private val slideNotesRepository: SlideNotesRepository
) {
    /**
     Converts the PPT into a SlideNote object, loads into the DB, and returns the slideNote ID for DB retrieval
     */
    suspend operator fun invoke(
        name: String,
        pptxObject: XMLSlideShow,
        conversionOption: ConversionOption,
    ): Long {
        return withContext(Dispatchers.IO) { // Make sure we're running on IO
            val slideNotes = parsePptxSpeakerNotes(
                pptxObject,
                getSlideParser(conversionOption)
            )
            slideNotesRepository.saveSlideNote( // implicit return of ID
                SlideNote(
                    name = name,
                    notes = slideNotes
                )
            )
        }
    }

    private fun getSlideParser(conversionOption: ConversionOption): (XSLFSlide) -> String {
        return { slide ->
            when (conversionOption) {
                ConversionOption.SpeakerNotes -> extractSpeakerNotesFromSlidesParser(slide)
                ConversionOption.SlideContent -> extractSlideContentFromSlidesParser(slide)
                else -> throw Exception("Not yet implemented") // TODO - implement for AI generation
            }
        }
    }

    private fun parsePptxSpeakerNotes(
        pptxObject: XMLSlideShow,
        parser: (XSLFSlide) -> String
    ): List<SlideNoteItem> {
        val slides = pptxObject.slides
        val res = mutableListOf<SlideNoteItem>()

        for (slide in slides) {
            val slideTitle = slide.title ?: ""
            val slideNotes = parser(slide)
            val slideNoteItem = SlideNoteItem(slideTitle, slideNotes)
            res.add(slideNoteItem)
        }
        return res
    }

    private fun extractSpeakerNotesFromSlidesParser(slide: XSLFSlide): String {
        val notes: XSLFNotes? = slide.notes
        if (notes != null) {
            val notesParagraphs = notes.textParagraphs
            val notesAsText = notesParagraphs.flatten().map{ it.text }
            return notesAsText.joinToString("\n")
        }
        return ""
    }

    private fun extractSlideContentFromSlidesParser(slide: XSLFSlide): String {
        return slide.shapes
            .filterIsInstance<XSLFTextShape>()
            .map { it.text ?: "" }
            .joinToString { "\n" }
    }
}