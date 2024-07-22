package com.visualrecursion.slidenotes.ui.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.data.entities.SlideNoteEntity

@Composable
fun SlideNoteNavDrawerItem(
    slideNoteEntity: SlideNoteEntity,
    onClickAction: () -> Unit,
    onDeleteAction: () -> Unit,
    selected: Boolean
) {
    NavigationDrawerItem(
        label = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = slideNoteEntity.name)
                IconButton(onClick = onDeleteAction) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = stringResource(
                            R.string.delete_note_accessibility,
                            slideNoteEntity.name
                        )
                    )
                }
            }
        },
        selected = selected,
        onClick = onClickAction
    )
}

@Preview
@Composable
fun SlideNoteNavDrawerItemPreview() {
    ModalDrawerSheet {
        SlideNoteNavDrawerItem(
            slideNoteEntity = SlideNoteEntity(
                id = 0,
                name = "Test"
            ),
            onClickAction = {},
            onDeleteAction = {},
            selected = false
        )
        SlideNoteNavDrawerItem(
            slideNoteEntity = SlideNoteEntity(
                id = 0,
                name = "Test"
            ),
            onClickAction = {},
            onDeleteAction = {},
            selected = true
        )
    }
}