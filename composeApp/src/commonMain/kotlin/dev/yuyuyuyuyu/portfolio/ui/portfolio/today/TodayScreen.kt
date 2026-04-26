package dev.yuyuyuyuyu.portfolio.ui.portfolio.today

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun TodayScreen(
    appsViewModel: AppsViewModel,
    pluginsViewModel: PluginsViewModel,
    onProductClick: (String) -> Unit,
) {
    val appsState by appsViewModel.uiState.collectAsState()
    val pluginsState by pluginsViewModel.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    val howOldAmI = appsState.apps.find { it.name == "年齢の計算" }
    val inputSourceHandler = appsState.apps.find { it.name == "Input Source Handler" }
    val composePwa = pluginsState.plugins.find { it.name == "ComposePWA" }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            // Header
            Text(
                text = "Today",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        item {
            // Developer Philosophy Card
            DeveloperPhilosophyCard()
        }

        if (howOldAmI != null) {
            item {
                AppOfTheDayCard(
                    app = howOldAmI,
                    onClick = { onProductClick(howOldAmI.repositoryUrl) })
            }
        }

        if (inputSourceHandler != null) {
            item {
                ToolOfTheDayCard(
                    product = inputSourceHandler,
                    title = "Macの便利ツール",
                    onClick = { onProductClick(inputSourceHandler.repositoryUrl) })
            }
        }

        if (composePwa != null) {
            item {
                ToolOfTheDayCard(
                    product = composePwa,
                    title = "開発のおとも",
                    onClick = { onProductClick(composePwa.repositoryUrl) })
            }
        }

        item {
            DeveloperProfileCard(
                onLinkClick = { url -> uriHandler.openUri(url) }
            )
        }
    }
}

@Composable
fun DeveloperProfileCard(onLinkClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column {
                Text(
                    text = "プロフィール",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.7f)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "小林 勇",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    text = "デベロッパーツールエンジニア",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.8f)
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "好きな技術: Kotlin, Compose Multiplatform for Web, Android",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                OutlinedButton(
                    onClick = { onLinkClick("https://github.com/yuyuyuyuyu-dev") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onTertiaryContainer)
                ) {
                    Icon(
                        Icons.Default.Code,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("GitHub")
                }

                OutlinedButton(
                    onClick = { onLinkClick("https://youtrust.jp/users/bb7902cca964b92558d0116a5f44f362") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onTertiaryContainer)
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("YOUTRUST")
                }

                OutlinedButton(
                    onClick = { onLinkClick("mailto:yu.kobayashi@yuyuyuyuyu.dev") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onTertiaryContainer)
                ) {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("メール")
                }
            }
        }
    }
}

@Composable
fun DeveloperPhilosophyCard(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.fillMaxWidth().height(400.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "私について",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "めんどくさいことはやりたくない。",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Text(
                text = "日常で出会ったちょっとした不便から開発時の繰り返しの作業まで、「めんどくさい」と思ったことをアプリやツールを作って解決しています。",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.5
            )
        }
    }
}

@Composable
fun AppOfTheDayCard(app: App, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().height(400.dp).clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (app.screenshots.isNotEmpty()) {
                Image(
                    painter = painterResource(app.screenshots.first()),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Gradient overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                startY = 100f
                            )
                        )
                )
            }

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = "今日のピックアップ",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = app.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f),
                    maxLines = 2
                )
            }
        }
    }
}

@Composable
fun ToolOfTheDayCard(
    product: Any,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val name = if (product is App) product.name else if (product is Product) product.name else ""
    val description =
        if (product is App) product.description else if (product is Product) product.description else ""

    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier.fillMaxWidth().height(250.dp).clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
