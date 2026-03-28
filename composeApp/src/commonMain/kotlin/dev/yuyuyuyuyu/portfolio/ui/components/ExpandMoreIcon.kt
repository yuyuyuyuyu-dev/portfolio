package dev.yuyuyuyuyu.portfolio.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun ExpandMoreIcon(
    expanded: Boolean,
    modifier: Modifier = Modifier,
) = Box(Modifier.clip(CircleShape).then(other = modifier)) {
    if (expanded) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "close",
        )
    } else {
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = "open",
        )
    }
}

private class ExpandMoreIconPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(true, false)
}

@Preview
@Composable
private fun ExpandMoreIconPreview(
    @PreviewParameter(ExpandMoreIconPreviewParameterProvider::class) expanded: Boolean,
) {
    ExpandMoreIcon(expanded = expanded)
}
