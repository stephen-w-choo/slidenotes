package com.visualrecursion.slidenotes.ui.screens.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.visualrecursion.slidenotes.DynamicUiValuesProvider
import com.visualrecursion.slidenotes.FontSizes
import com.visualrecursion.slidenotes.ui.screens.components.containers.PreviewContainer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesPager(
    pagerState: PagerState,
    notes: List<String>
) {
    Row(modifier = Modifier.fillMaxHeight()) {
        HorizontalPager(
            state = pagerState,
        ) { noteIndex ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        PaddingValues(
                            horizontal = 8.dp,
                            vertical = 16.dp
                        )
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                val dynamicFontSize = FontSizes[DynamicUiValuesProvider.current.fontScale]
                val LINE_SPACING = 1.15

                Text(
                    text = notes[noteIndex],
                    modifier = Modifier.padding(16.dp),
                    fontSize = dynamicFontSize.sp,
                    lineHeight = (dynamicFontSize * LINE_SPACING).sp
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(
    heightDp = 500,
    widthDp = 500,
)
@Composable
fun NotesPagerPreview() {
    val extraLongString = "test\ntest\ntest\ntest\ntest\ntest\ntest\n" +
            "test\ntest\ntest\ntest\ntest\ntest\ntest\ntest\n" +
            "test\ntest\ntest\ntest\ntest\ntest\ntest\ntest\n" +
            "test\ntest\ntest\ntest\ntest\ntest\ntest\ntest\n" +
            "test\ntest\ntest\ntest\ntest\ntest\ntest\ntest\n"


    PreviewContainer {
        NotesPager(
            pagerState = rememberPagerState { 2 },
            notes = listOf(extraLongString, "test 2")
        )
    }
}