package dev.yuyuyuyuyu.portfolio.ui.portfolio.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.AppTile
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.ProductTile
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModel

import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory

@Composable
fun CatalogScreen(
    appsViewModel: AppsViewModel,
    librariesViewModel: LibrariesViewModel,
    pluginsViewModel: PluginsViewModel,
    cliToolsViewModel: CliToolsViewModel,
    templatesViewModel: TemplatesViewModel,
    onProductClick: (String) -> Unit,
) {
    val appsState by appsViewModel.uiState.collectAsState()
    val librariesState by librariesViewModel.uiState.collectAsState()
    val pluginsState by pluginsViewModel.uiState.collectAsState()
    val cliToolsState by cliToolsViewModel.uiState.collectAsState()
    val templatesState by templatesViewModel.uiState.collectAsState()

    val allApps = appsState.apps

    // Group apps
    val macApps = allApps.filter { Platform.MacOS in it.platforms }
    val webApps = allApps.filter { Platform.Web in it.platforms }
    val androidIosApps = allApps.filter { Platform.Android in it.platforms || Platform.Ios in it.platforms }
    val otherApps = allApps - macApps.toSet() - webApps.toSet() - androidIosApps.toSet()

    // SSoT: Group by actual data properties
    val allProducts = librariesState.libraries + pluginsState.plugins + cliToolsState.cliTools + templatesState.templates
    val cliTools = allProducts.filter { it.category == ProductCategory.CliTool }.sortedBy { it.name }
    val plugins = allProducts.filter { it.category == ProductCategory.Plugin }.sortedBy { it.name }
    val libraries = allProducts.filter { it.category == ProductCategory.Library }.sortedBy { it.name }
    val templates = allProducts.filter { it.category == ProductCategory.Template }.sortedBy { it.name }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        if (webApps.isNotEmpty()) {
            item {
                CatalogSection(
                    title = "Webアプリ・PWA",
                    apps = webApps,
                    onProductClick = onProductClick
                )
            }
        }

        if (plugins.isNotEmpty()) {
            item {
                CatalogProductSection(
                    title = "プラグイン",
                    products = plugins,
                    icon = Icons.Default.Bolt,
                    iconDescription = "Plugin",
                    onProductClick = onProductClick
                )
            }
        }

        if (libraries.isNotEmpty()) {
            item {
                CatalogProductSection(
                    title = "ライブラリ",
                    products = libraries,
                    icon = Icons.Default.Book,
                    iconDescription = "Library",
                    onProductClick = onProductClick
                )
            }
        }

        if (cliTools.isNotEmpty()) {
            item {
                CatalogProductSection(
                    title = "CLIツール",
                    products = cliTools,
                    icon = Icons.Default.Terminal,
                    iconDescription = "CLI",
                    onProductClick = onProductClick
                )
            }
        }

        if (templates.isNotEmpty()) {
            item {
                CatalogProductSection(
                    title = "テンプレート",
                    products = templates,
                    icon = Icons.Default.Brush,
                    iconDescription = "Template",
                    onProductClick = onProductClick
                )
            }
        }

        if (macApps.isNotEmpty()) {
            item {
                CatalogSection(
                    title = "Macアプリ",
                    apps = macApps,
                    onProductClick = onProductClick
                )
            }
        }

        if (androidIosApps.isNotEmpty() || otherApps.isNotEmpty()) {
            item {
                CatalogSection(
                    title = "モバイル＆その他アプリ",
                    apps = androidIosApps + otherApps,
                    onProductClick = onProductClick
                )
            }
        }
    }
}

@Composable
fun CatalogSection(
    title: String,
    apps: List<App>,
    onProductClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(apps) { app ->
                AppTile(
                    app = app,
                    onClick = { onProductClick(app.repositoryUrl) }
                )
            }
        }
    }
}

@Composable
fun CatalogProductSection(
    title: String,
    products: List<Product>,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconDescription: String,
    onProductClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(products) { product ->
                ProductTile(
                    product = product,
                    icon = icon,
                    iconDescription = iconDescription,
                    onClick = { onProductClick(product.repositoryUrl) }
                )
            }
        }
    }
}
