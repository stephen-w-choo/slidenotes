package com.visualrecursion.slidenotes.ui

// Exceptions for the View/UI level logic

class DeletionFailedException(
    message: String = "Failed to delete file",
    cause: Throwable? = null
) : Exception(message, cause)

class EmptyFileException(
    message: String = "File appears to be empty or blank",
    cause: Throwable? = null
) : Exception(message, cause)

class FileParsingError(
    message: String = "Error parsing file",
    cause: Throwable? = null
) : Exception(message, cause)

class InvalidFileTypeException(
    fileExtension: String,
    message: String = "Invalid file type: $fileExtension. Only .pptx files are supported at the moment.",
    cause: Throwable?
) : Exception(message, cause)