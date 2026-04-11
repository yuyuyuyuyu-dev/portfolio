package dev.yuyuyuyuyu.portfolio.ui.components.listItems.shared

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.TechStack

@Composable
fun SupportingContent(
    description: String,
    techStackSet: Set<TechStack>,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(3.dp),
) {
    Text(description)
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        val techStacks = remember(techStackSet) { techStackSet.sortedBy { it.ordinal } }
        techStacks.forEach {
            Text(
                text = it.label,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(5.dp),
                    )
                    .padding(horizontal = 4.dp),
            )
        }
    }
}

@Preview
@Composable
private fun SupportingContentPreview() {
    SupportingContent(
        description = "This is a sample description.",
        techStackSet = setOf(TechStack.Kotlin, TechStack.JetpackCompose),
    )
}
