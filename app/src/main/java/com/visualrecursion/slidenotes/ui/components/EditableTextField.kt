package com.visualrecursion.slidenotes.ui.components

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity


@Composable
fun EditableTextField(
    slideNoteEntity: SlideNoteEntity
) {
    val newName = remember { mutableStateOf(slideNoteEntity.name) }
    val isBeingEdited = remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = newName.value,
        onValueChange = {
            newName.value = it
        },
        readOnly = !isBeingEdited.value,
        modifier = Modifier
            .focusRequester(focusRequester)
    )

    IconButton(onClick = { focusRequester.requestFocus() }) {

    }
}