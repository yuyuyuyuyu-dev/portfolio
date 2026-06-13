package dev.yuyuyuyuyu.portfolio.ui.portfolio.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.PortfolioItemIcon
import dev.yuyuyuyuyu.portfolio.utils.displayDescription
import dev.yuyuyuyuyu.portfolio.utils.displayMotivation
import dev.yuyuyuyuyu.portfolio.utils.displayName
import dev.yuyuyuyuyu.portfolio.utils.setPlainText
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ui_copy_command
import portfolio.composeapp.generated.resources.ui_installation
import portfolio.composeapp.generated.resources.ui_open
import portfolio.composeapp.generated.resources.ui_run_command
import portfolio.composeapp.generated.resources.ui_screenshots
import portfolio.composeapp.generated.resources.ui_why_built

@Composable
fun DetailScreen(item: PortfolioItem) {
    val scrollState = rememberScrollState()

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        DetailHeader(item = item)

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        if (item is App && item.screenshots.isNotEmpty()) {
            ScreenshotsSection(app = item)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }

        val motivation = item.displayMotivation
        if (!motivation.isNullOrBlank()) {
            MotivationSection(motivation = motivation)
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }

        val installCommand = item.installCommand
        if (!installCommand.isNullOrBlank()) {
            InstallCommandSection(installCommand = installCommand)
        }
    }
}

@Composable
private fun DetailHeader(item: PortfolioItem) {
    val uriHandler = LocalUriHandler.current
    val appUrl = (item as? App)?.url

    Row(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PortfolioItemIcon(item = item, size = 120.dp)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = item.displayName,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = item.displayDescription,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            // Action Buttons
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(top = 8.dp)) {
                if (appUrl != null) {
                    Button(onClick = { uriHandler.openUri(appUrl) }) {
                        Icon(Icons.AutoMirrored.Filled.OpenInNew, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(Res.string.ui_open))
                    }
                }
                OutlinedButton(onClick = { uriHandler.openUri(item.repositoryUrl) }) {
                    Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("GitHub")
                }
            }
        }
    }
}

@Composable
private fun ScreenshotsSection(app: App) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(Res.string.ui_screenshots),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            items(app.screenshots) { screenshot ->
                Image(
                    painter = painterResource(screenshot),
                    contentDescription = "Screenshot of ${app.displayName}",
                    modifier =
                        Modifier
                            .height(400.dp)
                            .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }
}

@Composable
private fun MotivationSection(motivation: String) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = stringResource(Res.string.ui_why_built),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = motivation,
            style = MaterialTheme.typography.bodyLarge,
            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.5,
        )
    }
}

@Composable
private fun InstallCommandSection(installCommand: String) {
    val clipboard = LocalClipboard.current
    val coroutineScope = rememberCoroutineScope()

    val commandTitle =
        if (installCommand.trim().startsWith("npx")) {
            stringResource(Res.string.ui_run_command)
        } else {
            stringResource(Res.string.ui_installation)
        }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = commandTitle,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                SelectionContainer(modifier = Modifier.fillMaxWidth().padding(end = 48.dp)) {
                    Text(
                        text = installCommand,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp),
                    )
                }
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            clipboard.setPlainText(installCommand)
                        }
                    },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = stringResource(Res.string.ui_copy_command),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}
