package com.visualrecursion.slidenotes.ui.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import com.visualrecursion.slidenotes.ui.screens.landing.startMenu.FileDetails
import java.util.Locale
import kotlin.math.ln

fun getFileExtension(context: Context, uri: Uri): String? {
    val contentResolver = context.contentResolver
    val type = contentResolver.getType(uri)
    return if (type != null) {
        MimeTypeMap.getSingleton().getExtensionFromMimeType(type)
    } else {
        // Handle the case where the MIME type is not available
        uri.path?.let {
            val dotIndex = it.lastIndexOf('.')
            if (dotIndex != -1) {
                it.substring(dotIndex + 1)
            } else {
                null
            }
        }
    }
}

fun getFileDetails(context: Context, uri: Uri): FileDetails {
    var fileName: String? = null
    var fileSize: Long? = null

    val cursor = context.contentResolver.query(uri, null, null, null, null, null)
    cursor?.use {
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
        cursor.moveToFirst()
        fileName = cursor.getString(nameIndex)
        fileSize = cursor.getLong(sizeIndex)
    }

    return FileDetails(fileName, fileSize)
}

fun Long?.convertBytes(): String? {
    if (this == null) return null

    if (this < 1024) return "$this B"

    val exp = (ln(this.toDouble()) / ln(1024.0)).toInt()
    val unitPrefix = "KMGTPE"[exp - 1]

    return String.format(
        Locale.US,
        "%.1f %sB",
        this / Math.pow(1024.0, exp.toDouble()),
        unitPrefix
    )
}
