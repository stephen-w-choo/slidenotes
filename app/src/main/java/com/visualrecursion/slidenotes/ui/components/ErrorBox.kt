package com.visualrecursion.slidenotes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualrecursion.slidenotes.ui.components.containers.PreviewContainer

@Composable
fun ErrorBox(errorMessage: String) {
    Row(
        Modifier.background(MaterialTheme.colorScheme.error)
    ) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.onError,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun ErrorBoxPreview() {
    PreviewContainer {
        ErrorBox(errorMessage = "Test error: something went wrong")
    }
}