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
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModel

import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.*

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
    val cliTools = allProducts.filter { it.category == ProductCategory.CliTool }.sortedBy { it.nameFallback }
    val plugins = allProducts.filter { it.category == ProductCategory.Plugin }.sortedBy { it.nameFallback }
    val libraries = allProducts.filter { it.category == ProductCategory.Library }.sortedBy { it.nameFallback }
    val templates = allProducts.filter { it.category == ProductCategory.Template }.sortedBy { it.nameFallback }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        if (webApps.isNotEmpty()) {
            item {
                CatalogSection(
                    title = stringResource(Res.string.ui_web_apps),
                    items = webApps,
                    onProductClick = onProductClick
                )
            }
        }

        if (plugins.isNotEmpty()) {
            item {
                CatalogSection(
                    title = stringResource(Res.string.ui_plugins),
                    items = plugins,
                    onProductClick = onProductClick
                )
            }
        }

        if (libraries.isNotEmpty()) {
            item {
                CatalogSection(
                    title = stringResource(Res.string.ui_libraries),
                    items = libraries,
                    onProductClick = onProductClick
                )
            }
        }

        if (cliTools.isNotEmpty()) {
            item {
                CatalogSection(
                    title = stringResource(Res.string.ui_cli_tools),
                    items = cliTools,
                    onProductClick = onProductClick
                )
            }
        }

        if (templates.isNotEmpty()) {
            item {
                CatalogSection(
                    title = stringResource(Res.string.ui_templates),
                    items = templates,
                    onProductClick = onProductClick
                )
            }
        }

        if (macApps.isNotEmpty()) {
            item {
                CatalogSection(
                    title = stringResource(Res.string.ui_mac_apps),
                    items = macApps,
                    onProductClick = onProductClick
                )
            }
        }

        if (androidIosApps.isNotEmpty() || otherApps.isNotEmpty()) {
            item {
                CatalogSection(
                    title = stringResource(Res.string.ui_mobile_other_apps),
                    items = androidIosApps + otherApps,
                    onProductClick = onProductClick
                )
            }
        }
    }
}

@Composable
fun CatalogSection(
    title: String,
    items: List<dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem>,
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
            items(items) { item ->
                dev.yuyuyuyuyu.portfolio.ui.components.listItems.PortfolioItemTile(
                    item = item,
                    onClick = { onProductClick(item.repositoryUrl) }
                )
            }
        }
    }
}
