package com.visualrecursion.slidenotes.ui.navigation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.visualrecursion.slidenotes.R
import com.visualrecursion.slidenotes.ui.components.containers.PreviewContainer

@Composable
fun CtaButton(
    onClick: () -> Unit,
    text: String,
    rectangle: Boolean = true,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
    subtext: String? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.filledTonalButtonColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = if (rectangle) {
            RoundedCornerShape(8.dp)
        } else {
            ButtonDefaults.elevatedShape
        },
        modifier = modifier
            .heightIn(min = 64.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            leftIcon?.let {
                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp),
                    )
                } else {
                    Icon(
                        painter = painterResource(id = leftIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(24.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge
                )
                if (subtext != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = subtext,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            rightIcon?.let {
                Icon(
                    painter = painterResource(id = rightIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CtaButtonPreview() {
    PreviewContainer {
        CtaButton(
            {},
            leftIcon = R.drawable.ic_add_new,
            text = "Main text",
            subtext = "Subtext"
        )

        CtaButton(
            {},
            leftIcon = R.drawable.ic_add_new,
            rightIcon = R.drawable.ic_arrow_right,
            text = "Main text",
            subtext = "Subtext"
        )

        CtaButton(
            {},
            rightIcon = R.drawable.ic_arrow_right,
            text = "Main text",
            subtext = "Subtext"
        )
    }
}

@Preview
@Composable
fun CtaButtonPreview_Dark() {
    PreviewContainer(
        darkTheme = true
    ) {
        CtaButton(
            {},
            leftIcon = R.drawable.ic_add_new,
            text = "Main text",
            subtext = "Subtext"
        )

        CtaButton(
            {},
            leftIcon = R.drawable.ic_add_new,
            rightIcon = R.drawable.ic_arrow_right,
            text = "Main text",
            subtext = "Subtext"
        )

        CtaButton(
            {},
            rightIcon = R.drawable.ic_arrow_right,
            text = "Main text",
            subtext = "Subtext"
        )
    }
}