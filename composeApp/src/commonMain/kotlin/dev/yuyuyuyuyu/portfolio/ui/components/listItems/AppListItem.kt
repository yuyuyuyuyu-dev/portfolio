package dev.yuyuyuyuyu.portfolio.ui.components.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.shared.BaseListItem
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.shared.ItemBody
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.default_icon

@Composable
fun AppListItem(
    app: App,
    modifier: Modifier = Modifier,
) = BaseListItem(
    name = app.name,
    description = app.description,
    techStackSet = app.techStack,
    icon = {
        Image(
            painter = painterResource(app.icon),
            contentDescription = "app icon",
            modifier = it,
            contentScale = ContentScale.Crop,
        )
    },
    modifier = modifier,
) { uriHandler ->
    ItemBody(
        repositoryUrl = app.repositoryUrl,
        onSourceCodeLinkClick = { uriHandler.openUri(app.repositoryUrl) },
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            if (app.url != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        content = {
                            Row {
                                Icon(Icons.AutoMirrored.Default.OpenInNew, "open in new tab")
                                Spacer(Modifier.width(1.dp))
                                Text("アプリページを開く")
                            }
                        },
                        onClick = { uriHandler.openUri(app.url) },
                    )
                }
            }

            if (app.screenshots.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("スクリーンショット")

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                    ) {
                        items(app.screenshots) { screenshot ->
                            Image(painterResource(screenshot), "${app.name}'s screenshot")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AppListItemPreview() {
    AppListItem(
        app = App(
            name = "Sample App",
            description = "This is a sample app description.",
            techStack = setOf(TechStack.Kotlin, TechStack.JetpackCompose),
            repositoryUrl = "https://github.com/example/repo",
            icon = Res.drawable.default_icon,
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.App,
            platforms = emptySet(),
        )
    )
}
