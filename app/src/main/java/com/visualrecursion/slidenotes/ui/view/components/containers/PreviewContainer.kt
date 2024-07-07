package com.visualrecursion.slidenotes.ui.view.components.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.visualrecursion.slidenotes.ui.theme.SlideNotesTheme

@Composable
fun PreviewContainer(children: @Composable () -> Unit) {
    SlideNotesTheme {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            children()
        }
    }
}