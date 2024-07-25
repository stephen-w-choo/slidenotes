package com.visualrecursion.slidenotes.ui.navigation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.ui.components.containers.PreviewContainer

@Composable
fun MakeNewSlideNoteButton(
    onClick: () -> Unit,
) {
    NavigationDrawerItem(
        icon = {
            Image(
                painter = painterResource(id = R.drawable.ic_slidenote_logo),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        },
        label = {
            Text(text = stringResource(R.string.make_a_new_slidenote))
        },
        selected = true, // TODO - change to false, but just put a different background on it
        onClick = onClick,
        modifier = Modifier.padding(vertical = 20.dp)
    )
}

@Preview
@Composable
fun MakeNewSlideNoteButtonPreview() {
    PreviewContainer {
        MakeNewSlideNoteButton { }
    }
}