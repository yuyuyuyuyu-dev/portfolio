package dev.yuyuyuyuyu.portfolio.ui.portfolio.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.Product
import org.jetbrains.compose.resources.painterResource

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    app: App? = null,
    product: Product? = null,
) {
    val uriHandler = LocalUriHandler.current
    val clipboardManager = LocalClipboardManager.current
    val scrollState = rememberScrollState()

    // Normalize data between App and Product for unified rendering
    val name = app?.name ?: product?.name ?: ""
    val description = app?.description ?: product?.description ?: ""
    val motivation = app?.motivation ?: product?.motivation
    val repositoryUrl = app?.repositoryUrl ?: product?.repositoryUrl ?: ""
    val installCommand = app?.installCommand ?: product?.installCommand
    val appUrl = app?.url

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // --- Header Section ---
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (app != null) {
                Image(
                    painter = painterResource(app.icon),
                    contentDescription = "$name icon",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(28.dp)),
                    contentScale = ContentScale.Crop,
                )
            } else {
                // Terminal Window Style Box
                Column(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFFBF1C7)) // Gruvbox Light background
                        .padding(12.dp)
                ) {
                    // macOS Window Buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFCC241D)))
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFD79921)))
                        Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFF98971A)))
                    }
                    
                    // Terminal Prompt
                    Text(
                        text = "> $name",
                        color = Color(0xFF3C3836), // Gruvbox Light text color
                        fontSize = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 20.sp
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                // Action Buttons
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.padding(top = 8.dp)) {
                    if (appUrl != null) {
                        Button(onClick = { uriHandler.openUri(appUrl) }) {
                            Text("開く")
                        }
                    }
                    OutlinedButton(onClick = { uriHandler.openUri(repositoryUrl) }) {
                        Icon(Icons.Default.Code, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("GitHub")
                    }
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        // --- Screenshots Section (Only for Apps) ---
        if (app != null && app.screenshots.isNotEmpty()) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "スクリーンショット",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(app.screenshots) { screenshot ->
                        Image(
                            painter = painterResource(screenshot),
                            contentDescription = "Screenshot of $name",
                            modifier = Modifier
                                .height(400.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }

        // --- Motivation / Story Section ---
        if (!motivation.isNullOrBlank()) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "なぜ作ったのか？",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = motivation,
                    style = MaterialTheme.typography.bodyLarge,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.5
                )
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        }

        // --- Install/Run Command Section ---
        if (!installCommand.isNullOrBlank()) {
            val commandTitle = if (installCommand.trim().startsWith("npx")) "実行コマンド" else "インストール"
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = commandTitle,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                        SelectionContainer(modifier = Modifier.fillMaxWidth().padding(end = 48.dp)) {
                            Text(
                                text = installCommand,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        IconButton(
                            onClick = { clipboardManager.setText(AnnotatedString(installCommand)) },
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Copy command",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}
