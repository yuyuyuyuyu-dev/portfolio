package dev.yuyuyuyuyu.portfolio.ui.portfolio.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextOverflow
import coil3.compose.AsyncImagePainter
import com.mikepenz.markdown.coil3.Coil3ImageTransformerImpl
import com.mikepenz.markdown.compose.components.MarkdownComponentModel
import com.mikepenz.markdown.compose.components.markdownComponents
import com.mikepenz.markdown.compose.elements.MarkdownHeader
import com.mikepenz.markdown.compose.elements.MarkdownTable
import com.mikepenz.markdown.compose.elements.MarkdownTableHeader
import com.mikepenz.markdown.compose.elements.MarkdownTableRow
import com.mikepenz.markdown.m3.Markdown
import com.mikepenz.markdown.model.ImageTransformer
import dev.yuyuyuyuyu.portfolio.data.models.Readme
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.intellij.markdown.MarkdownTokenTypes
import org.intellij.markdown.ast.findChildOfType
import org.intellij.markdown.ast.getTextInNode
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ui_readme_load_failed

@Composable
fun ReadmeSection(
    readme: Readme,
    onHeadingLinkClick: (LayoutCoordinates) -> Unit,
) {
    val japanese = Locale.current.language == "ja"
    val markdown by produceState<Result<String>?>(null, readme, japanese) {
        value =
            runCatching {
                if (japanese) {
                    Res.readBytes(readme.japaneseFile).decodeToString()
                } else {
                    HttpClient { expectSuccess = true }.use { it.get(readme.rawUrl).bodyAsText() }
                }
            }
    }

    when (val result = markdown) {
        null -> {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        else -> {
            result
                .onSuccess { ReadmeMarkdown(readme = readme, markdown = it, onHeadingLinkClick = onHeadingLinkClick) }
                .onFailure {
                    Text(
                        text = stringResource(Res.string.ui_readme_load_failed),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
        }
    }
}

@Composable
private fun ReadmeMarkdown(
    readme: Readme,
    markdown: String,
    onHeadingLinkClick: (LayoutCoordinates) -> Unit,
) {
    val headings = remember(markdown) { mutableMapOf<String, LayoutCoordinates>() }
    val uriHandler = LocalUriHandler.current
    val readmeUriHandler =
        remember(readme, uriHandler, onHeadingLinkClick) {
            object : UriHandler {
                override fun openUri(uri: String) {
                    val heading = if (uri.startsWith("#")) headings[uri.drop(1)] else null
                    if (heading != null) {
                        onHeadingLinkClick(heading)
                    } else {
                        uriHandler.openUri(readme.resolve(uri))
                    }
                }
            }
        }

    CompositionLocalProvider(LocalUriHandler provides readmeUriHandler) {
        Markdown(
            content = markdown,
            modifier = Modifier.fillMaxWidth(),
            imageTransformer = ReadmeImageTransformer,
            components =
                markdownComponents(
                    heading1 = { ReadmeHeading(it, it.typography.h1, headings) },
                    heading2 = { ReadmeHeading(it, it.typography.h2, headings) },
                    heading3 = { ReadmeHeading(it, it.typography.h3, headings) },
                    heading4 = { ReadmeHeading(it, it.typography.h4, headings) },
                    heading5 = { ReadmeHeading(it, it.typography.h5, headings) },
                    heading6 = { ReadmeHeading(it, it.typography.h6, headings) },
                    table = { ReadmeTable(it) },
                ),
        )
    }
}

@Composable
private fun ReadmeHeading(
    model: MarkdownComponentModel,
    style: TextStyle,
    headings: MutableMap<String, LayoutCoordinates>,
) {
    val anchor =
        model.node
            .findChildOfType(MarkdownTokenTypes.ATX_CONTENT)
            ?.getTextInNode(model.content)
            ?.toString()
            ?.toAnchor()

    Box(modifier = Modifier.onGloballyPositioned { coordinates -> anchor?.let { headings[it] = coordinates } }) {
        MarkdownHeader(content = model.content, node = model.node, style = style)
    }
}

@Composable
private fun ReadmeTable(model: MarkdownComponentModel) {
    MarkdownTable(
        content = model.content,
        node = model.node,
        style = model.typography.table,
        headerBlock = { content, header, tableWidth, style ->
            MarkdownTableHeader(
                content = content,
                header = header,
                tableWidth = tableWidth,
                style = style,
                maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Clip,
            )
        },
        rowBlock = { content, header, tableWidth, style ->
            MarkdownTableRow(
                content = content,
                header = header,
                tableWidth = tableWidth,
                style = style,
                maxLines = Int.MAX_VALUE,
                overflow = TextOverflow.Clip,
            )
        },
    )
}

private object ReadmeImageTransformer : ImageTransformer {
    @Composable
    override fun transform(link: String) = Coil3ImageTransformerImpl.transform(link)

    @Composable
    override fun intrinsicSize(painter: Painter): Size {
        val state = (painter as? AsyncImagePainter)?.state?.collectAsState()?.value
        return if (state is AsyncImagePainter.State.Error) {
            Size.Zero
        } else {
            Coil3ImageTransformerImpl.intrinsicSize(painter)
        }
    }
}

private fun String.toAnchor() =
    trim()
        .lowercase()
        .filter { it.isLetterOrDigit() || it == ' ' || it == '-' || it == '_' }
        .replace(' ', '-')

private fun Readme.resolve(uri: String) =
    when {
        uri.contains("://") || uri.startsWith("mailto:") -> uri
        uri.startsWith("#") -> pageUrl + uri
        else -> pageUrl.substringBeforeLast('/') + "/" + uri
    }
