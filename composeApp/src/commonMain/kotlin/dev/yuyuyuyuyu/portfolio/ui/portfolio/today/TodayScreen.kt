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
import androidx.compose.foundation.lazy.LazyListScope
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioViewModels
import dev.yuyuyuyuyu.portfolio.utils.displayDescription
import dev.yuyuyuyuyu.portfolio.utils.displayName
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name_howoldami
import portfolio.composeapp.generated.resources.app_name_notpullingcalc
import portfolio.composeapp.generated.resources.ui_about_me
import portfolio.composeapp.generated.resources.ui_app_of_the_day
import portfolio.composeapp.generated.resources.ui_dev_companion
import portfolio.composeapp.generated.resources.ui_email_link
import portfolio.composeapp.generated.resources.ui_familiar_tools
import portfolio.composeapp.generated.resources.ui_mac_utility
import portfolio.composeapp.generated.resources.ui_name
import portfolio.composeapp.generated.resources.ui_philosophy_body
import portfolio.composeapp.generated.resources.ui_philosophy_headline
import portfolio.composeapp.generated.resources.ui_profile
import portfolio.composeapp.generated.resources.ui_role
import portfolio.composeapp.generated.resources.ui_skills
import portfolio.composeapp.generated.resources.ui_ultimate_laziness

@Composable
fun TodayScreen(
    viewModels: PortfolioViewModels,
    onProductClick: (String) -> Unit,
) {
    val appsState by viewModels.apps.uiState.collectAsState()
    val pluginsState by viewModels.plugins.uiState.collectAsState()
    val cliToolsState by viewModels.cliTools.uiState.collectAsState()
    val templatesState by viewModels.templates.uiState.collectAsState()
    val uriHandler = LocalUriHandler.current

    val featuredItems =
        TodayFeaturedItems(
            howOldAmI = appsState.apps.find { it.nameRes == Res.string.app_name_howoldami },
            html2pdf = cliToolsState.cliTools.find { it.nameFallback == "@yuyuyuyuyu-dev/html2pdf" },
            notPullingCalc = appsState.apps.find { it.nameRes == Res.string.app_name_notpullingcalc },
            inputSourceHandler = appsState.apps.find { it.nameFallback == "Input Source Handler" },
            composePwa = pluginsState.plugins.find { it.nameFallback == "ComposePWA" },
            businessCard = templatesState.templates.find { it.nameFallback == "business-card-template" },
        )

    TodayScreenContent(
        featuredItems = featuredItems,
        onProductClick = onProductClick,
        onLinkClick = { url -> uriHandler.openUri(url) },
    )
}

@Composable
fun TodayScreenContent(
    featuredItems: TodayFeaturedItems,
    onProductClick: (String) -> Unit,
    onLinkClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        item {
            // Header
            Text(
                text = "Today",
                style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }

        item {
            // Developer Philosophy Card
            DeveloperPhilosophyCard()
        }

        featuredProductItems(
            featuredItems = featuredItems,
            onProductClick = onProductClick,
        )

        item {
            DeveloperProfileCard(
                onLinkClick = onLinkClick,
            )
        }
    }
}

private fun LazyListScope.featuredProductItems(
    featuredItems: TodayFeaturedItems,
    onProductClick: (String) -> Unit,
) {
    featuredItems.howOldAmI?.let { app ->
        item {
            AppOfTheDayCard(
                app = app,
                onClick = { onProductClick(app.repositoryUrl) },
            )
        }
    }

    featuredItems.html2pdf?.let { product ->
        item {
            ToolOfTheDayCard(
                product = product,
                title = stringResource(Res.string.ui_ultimate_laziness),
                onClick = { onProductClick(product.repositoryUrl) },
            )
        }
    }

    featuredItems.notPullingCalc?.let { app ->
        item {
            AppOfTheDayCard(
                app = app,
                onClick = { onProductClick(app.repositoryUrl) },
            )
        }
    }

    featuredItems.composePwa?.let { product ->
        item {
            ToolOfTheDayCard(
                product = product,
                title = stringResource(Res.string.ui_dev_companion),
                onClick = { onProductClick(product.repositoryUrl) },
            )
        }
    }

    featuredItems.businessCard?.let { product ->
        item {
            ToolOfTheDayCard(
                product = product,
                title = stringResource(Res.string.ui_familiar_tools),
                onClick = { onProductClick(product.repositoryUrl) },
            )
        }
    }

    featuredItems.inputSourceHandler?.let { product ->
        item {
            ToolOfTheDayCard(
                product = product,
                title = stringResource(Res.string.ui_mac_utility),
                onClick = { onProductClick(product.repositoryUrl) },
            )
        }
    }
}

@Composable
fun DeveloperProfileCard(
    onLinkClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Column {
                Text(
                    text = stringResource(Res.string.ui_profile),
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.7f),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(Res.string.ui_name),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
                Text(
                    text = stringResource(Res.string.ui_role),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.8f),
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(Res.string.ui_skills),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }

            ProfileLinks(
                onLinkClick = onLinkClick,
                modifier = Modifier.padding(top = 8.dp),
            )
        }
    }
}

@Composable
private fun ProfileLinks(
    onLinkClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        ProfileLinkButton(
            icon = Icons.Default.Person,
            label = "X",
            onClick = { onLinkClick("https://x.com/yuyuyuyuyu_dev") },
        )
        ProfileLinkButton(
            icon = Icons.Default.Code,
            label = "GitHub",
            onClick = { onLinkClick("https://github.com/yuyuyuyuyu-dev") },
        )
        ProfileLinkButton(
            icon = Icons.Default.Person,
            label = "YOUTRUST",
            onClick = { onLinkClick("https://youtrust.jp/users/bb7902cca964b92558d0116a5f44f362") },
        )
        ProfileLinkButton(
            icon = Icons.Default.Email,
            label = stringResource(Res.string.ui_email_link),
            onClick = { onLinkClick("mailto:yu.kobayashi@yuyuyuyuyu.dev") },
        )
    }
}

@Composable
private fun ProfileLinkButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onTertiaryContainer),
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
        )
        Spacer(Modifier.width(8.dp))
        Text(label)
    }
}

@Composable
fun DeveloperPhilosophyCard(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.fillMaxWidth().height(400.dp),
    ) {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = stringResource(Res.string.ui_about_me),
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(Res.string.ui_philosophy_headline),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }

            Text(
                text = stringResource(Res.string.ui_philosophy_body),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * 1.5,
            )
        }
    }
}

@Composable
fun AppOfTheDayCard(
    app: App,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.fillMaxWidth().height(400.dp).clickable { onClick() },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (app.screenshots.isNotEmpty()) {
                Image(
                    painter = painterResource(app.screenshots.first()),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                // Gradient overlay
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                                    startY = 100f,
                                ),
                            ),
                )
            }

            Column(
                modifier =
                    Modifier
                        .padding(24.dp)
                        .align(Alignment.BottomStart),
            ) {
                Text(
                    text = stringResource(Res.string.ui_app_of_the_day),
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White.copy(alpha = 0.8f),
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = app.displayName,
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = app.displayDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.9f),
                    maxLines = 2,
                )
            }
        }
    }
}

@Composable
fun ToolOfTheDayCard(
    product: PortfolioItem,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        modifier = modifier.fillMaxWidth().height(250.dp).clickable { onClick() },
    ) {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f),
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = product.displayName,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = product.displayDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        }
    }
}
