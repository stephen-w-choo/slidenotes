package com.visualrecursion.slidenotes.ui.components.containers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.SlideNotesTheme

@Composable
fun PreviewContainer(children: @Composable () -> Unit) {
    SlideNotesTheme {
        Column(
            modifier = Modifier
                .height(1900.dp)
                .width(720.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            children()
        }
    }
}