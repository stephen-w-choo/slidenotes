package com.visualrecursion.slidenotes.domain

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.xslf.usermodel.XMLSlideShow
import javax.inject.Inject

class ConvertPptUseCase @Inject constructor(
    @ApplicationContext val context: Context
) {
    suspend operator fun invoke(uri: Uri): String? {
        return withContext(Dispatchers.IO) { // Make sure we're running on IO
            val contentResolver = context.contentResolver
            val inputStream = contentResolver.openInputStream(uri)
            val delayedResult = inputStream?.use { stream ->
                val ppt = XMLSlideShow(stream)
                val slides = ppt.slides
                val textBuilder = StringBuilder()
                for (slide in slides) {
                    val shapes = slide.shapes
                    for (shape in shapes) {
                        if (shape is org.apache.poi.xslf.usermodel.XSLFTextShape) {
                            textBuilder.append(shape.text)
                            textBuilder.append("\n")
                        }
                    }
                }
                textBuilder.toString()
            }
            delayedResult // implicit return
        }
    }
}