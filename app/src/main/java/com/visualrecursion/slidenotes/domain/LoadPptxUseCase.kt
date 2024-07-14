package com.visualrecursion.slidenotes.domain

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.poi.xslf.usermodel.XMLSlideShow
import java.io.InputStream
import javax.inject.Inject

class LoadPptxUseCase @Inject constructor(
    @ApplicationContext val appContext: Context
) {
    suspend operator fun invoke(uri: Uri): XMLSlideShow? {
        return withContext(Dispatchers.IO) { // Make sure we're running on IO
            val contentResolver = appContext.contentResolver
            val inputStream = contentResolver.openInputStream(uri)

            if (inputStream != null) {
                parseInputStream(inputStream) // implicit return
            } else {
                null
            }
        }
    }

    private fun parseInputStream(inputStream: InputStream): XMLSlideShow {
        inputStream.use { stream ->
            val pptObject = XMLSlideShow(stream)
            return pptObject
        }
    }
}