package com.visualrecursion.slidenotes.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualrecursion.slidenotes.ui.theme.DynamicUiValuesProvider
import com.visualrecursion.slidenotes.ui.theme.FontSizes
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.ui.theme.ThemeActions
import com.visualrecursion.slidenotes.ui.components.CustomIconButton
import com.visualrecursion.slidenotes.ui.components.containers.PreviewContainer
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    toggleNavDrawer: () -> Unit,
    themeActions: ThemeActions
) {
    Column {
        Spacer(Modifier.statusBarsPadding())
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
               onClick = { toggleNavDrawer() }
            ) {
                Text("test")
            }
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
                modifier = Modifier.widthIn(max = 220.dp),
            )
            Icon(
                painter = painterResource(R.drawable.ic_font_size_increase),
                contentDescription = null
            )
            CustomIconButton(
                onClick = { themeActions.switchTheme() },
                isActive = DynamicUiValuesProvider.current.useDarkMode
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_dark_mode),
                    contentDescription = stringResource(R.string.dark_mode_accessibility)
                )
            }
        }
    }
}

@Preview(widthDp = 500)
@Composable
fun TopBarPreview() {
    PreviewContainer {
        TopBar(toggleNavDrawer = {}, themeActions = ThemeActions({}, {}))
    }
}