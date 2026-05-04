package dev.yuyuyuyuyu.portfolio.ui.components.listItems.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import dev.yuyuyuyuyu.portfolio.ui.components.ExpandMoreIcon

@Composable
fun BaseListItem(
    name: String,
    description: String,
    techStackSet: Set<TechStack>,
    icon: @Composable (modifier: Modifier) -> Unit,
    modifier: Modifier = Modifier,
    expandedItem: @Composable (uriHandler: UriHandler) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    val interactionSource = remember { MutableInteractionSource() }
    val onClick = { expanded = !expanded }
    Column(
        modifier =
            modifier
                .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
                .background(MaterialTheme.colorScheme.surface)
                .semantics {
                    stateDescription = if (expanded) "展開済み" else "折りたたみ済み"
                    onClick(label = if (expanded) "たたむ" else "開く") {
                        onClick()
                        true
                    }
                },
    ) {
        ListItem(
            leadingContent = {
                icon(
                    Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                )
            },
            headlineContent = { Text(name) },
            supportingContent = {
                SupportingContent(
                    description = description,
                    techStackSet = techStackSet,
                )
            },
            trailingContent = {
                ExpandMoreIcon(
                    expanded = expanded,
                    modifier =
                        Modifier.indication(
                            interactionSource = interactionSource,
                            indication = LocalIndication.current,
                        ),
                )
            },
        )

        AnimatedVisibility(visible = expanded) {
            expandedItem(uriHandler)
        }

        Spacer(Modifier.height(40.dp))
    }
}

@Preview
@Composable
private fun BaseListItemPreview() {
    BaseListItem(
        name = "Sample Name",
        description = "This is a sample description.",
        techStackSet = setOf(TechStack.Kotlin, TechStack.JetpackCompose),
        icon = { modifier ->
            Spacer(modifier = modifier.background(MaterialTheme.colorScheme.primary))
        },
        expandedItem = {
            Text("Expanded Item Content")
        },
    )
}
