package com.visualrecursion.slidenotes.ui.scaffold

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualrecursion.slidenotes.DynamicUiValuesProvider
import com.visualrecursion.slidenotes.FontSizes
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.ThemeActions
import com.visualrecursion.slidenotes.ui.screens.components.containers.PreviewContainer
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    themeActions: ThemeActions
) {
    Column {
        Spacer(Modifier.statusBarsPadding())
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_font_size_decrease),
                contentDescription = null
            )
            Slider(
                value = DynamicUiValuesProvider.current.fontScale.toFloat(),
                onValueChange = { float ->
                    themeActions.changeFontSize(float.roundToInt())
                },
                valueRange = 0f..(FontSizes.size.toFloat() - 1),
                modifier = Modifier.widthIn(max = 250.dp)
            )
            Icon(
                painter = painterResource(R.drawable.ic_font_size_increase),
                contentDescription = null
            )
            IconButton(
                onClick = { /* TODO */ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_change_font),
                    contentDescription = stringResource(R.string.change_font_size_accessibility)
                )
            }
            IconButton(onClick = { themeActions.switchTheme() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dark_mode),
                    contentDescription = stringResource(R.string.dark_mode_accessibility)
                )
            }
        }
    }

//    TopAppBar(
//        title = {
//
//        },
//        colors = TopAppBarDefaults.topAppBarColors().copy(
//            containerColor = MaterialTheme.colorScheme.secondaryContainer
//        )
//    )
}

@Preview(widthDp = 500)
@Composable
fun TopBarPreview() {
    PreviewContainer {
        TopBar(themeActions = ThemeActions({}, {}))
    }
}