package com.visualrecursion.slidenotes.ui.utils

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap

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