package dev.yuyuyuyuyu.portfolio.ui.components.listItems.shared

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemBody(
    repositoryUrl: String,
    modifier: Modifier = Modifier,
    sourceCodeLabel: String = "ソースコード",
    onSourceCodeLinkClick: () -> Unit,
    additionalContent: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        additionalContent()

        val interactionSource = remember { MutableInteractionSource() }
        Column(
            modifier =
                Modifier
                    .padding(horizontal = 10.dp)
                    .clickable(interactionSource = interactionSource, indication = null, onClick = onSourceCodeLinkClick)
                    .semantics {
                        onClick(label = "ソースコードのページを新しいタブで開く") {
                            onSourceCodeLinkClick()
                            true
                        }
                    },
        ) {
            Text(
                text = sourceCodeLabel,
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier =
                    Modifier.indication(
                        interactionSource = interactionSource,
                        indication = LocalIndication.current,
                    ),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.OpenInNew,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                )
                Spacer(Modifier.width(1.dp))
                Text(
                    text = repositoryUrl,
                    color = MaterialTheme.colorScheme.secondary,
                    textDecoration = TextDecoration.Underline,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemBodyPreview() {
    ItemBody(
        repositoryUrl = "https://github.com/example/repo",
        onSourceCodeLinkClick = {},
    ) {
        Text("Additional Content")
    }
}
