package com.visualrecursion.slidenotes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isActive: Boolean,
    children: @Composable () -> Unit,
) {
    val buttonModifier = if (isActive) {
            modifier.clip(CircleShape)
                .background(MaterialTheme.colorScheme.outlineVariant)
        } else {
            modifier
        }
    IconButton(
        onClick = onClick,
        modifier = buttonModifier
    ) {
        children()
    }
}
