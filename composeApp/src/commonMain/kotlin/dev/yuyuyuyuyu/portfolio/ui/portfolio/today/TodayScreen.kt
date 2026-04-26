package dev.yuyuyuyuyu.portfolio.ui.portfolio.today

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
                AppOfTheDayCard(app = howOldAmI, onClick = { onProductClick(howOldAmI.repositoryUrl) })
            }
        }
        
        if (inputSourceHandler != null) {
            item {
                ToolOfTheDayCard(product = inputSourceHandler, title = "MAC UTILITY", onClick = { onProductClick(inputSourceHandler.repositoryUrl) })
            }
        }

        if (composePwa != null) {
            item {
                ToolOfTheDayCard(product = composePwa, title = "DEVELOPER TOOL", onClick = { onProductClick(composePwa.repositoryUrl) })
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
                    text = "MEET THE DEVELOPER",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "徹底的に、繰り返しを駆逐する。",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            
            Text(
                text = "「二度と同じことをしないために、コードを書く。」\n\n日常の面倒から開発プロセスの非効率まで、すべての定型作業を終わらせるためにアプリやツールを作り続けるエンジニアのポートフォリオ。",
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
                    text = "APP OF THE DAY",
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
fun ToolOfTheDayCard(product: Any, title: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val name = if (product is App) product.name else if (product is Product) product.name else ""
    val description = if (product is App) product.description else if (product is Product) product.description else ""

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
