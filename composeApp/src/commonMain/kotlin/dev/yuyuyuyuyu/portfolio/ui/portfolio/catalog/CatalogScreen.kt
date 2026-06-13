package dev.yuyuyuyuyu.portfolio.ui.portfolio.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.PortfolioItemTile
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioViewModels
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ui_cli_tools
import portfolio.composeapp.generated.resources.ui_libraries
import portfolio.composeapp.generated.resources.ui_mac_apps
import portfolio.composeapp.generated.resources.ui_mobile_other_apps
import portfolio.composeapp.generated.resources.ui_plugins
import portfolio.composeapp.generated.resources.ui_templates
import portfolio.composeapp.generated.resources.ui_web_apps

@Composable
fun CatalogScreen(
    viewModels: PortfolioViewModels,
    onProductClick: (String) -> Unit,
) {
    val appsState by viewModels.apps.uiState.collectAsState()
    val librariesState by viewModels.libraries.uiState.collectAsState()
    val pluginsState by viewModels.plugins.uiState.collectAsState()
    val cliToolsState by viewModels.cliTools.uiState.collectAsState()
    val templatesState by viewModels.templates.uiState.collectAsState()

    CatalogScreenContent(
        allApps = appsState.apps,
        allProducts = librariesState.libraries + pluginsState.plugins + cliToolsState.cliTools + templatesState.templates,
        onProductClick = onProductClick,
    )
}

@Composable
fun CatalogScreenContent(
    allApps: List<PortfolioItem>,
    allProducts: List<PortfolioItem>,
    onProductClick: (String) -> Unit,
) {
    // Group apps
    val macApps = allApps.filter { Platform.MacOS in it.platforms }
    val webApps = allApps.filter { Platform.Web in it.platforms }
    val androidIosApps = allApps.filter { Platform.Android in it.platforms || Platform.Ios in it.platforms }
    val otherApps = allApps - macApps.toSet() - webApps.toSet() - androidIosApps.toSet()

    // SSoT: Group by actual data properties
    val cliTools = allProducts.filter { it.category == ProductCategory.CliTool }.sortedBy { it.nameFallback }
    val plugins = allProducts.filter { it.category == ProductCategory.Plugin }.sortedBy { it.nameFallback }
    val libraries = allProducts.filter { it.category == ProductCategory.Library }.sortedBy { it.nameFallback }
    val templates = allProducts.filter { it.category == ProductCategory.Template }.sortedBy { it.nameFallback }

    val sections =
        listOf(
            Res.string.ui_web_apps to webApps,
            Res.string.ui_plugins to plugins,
            Res.string.ui_libraries to libraries,
            Res.string.ui_cli_tools to cliTools,
            Res.string.ui_templates to templates,
            Res.string.ui_mac_apps to macApps,
            Res.string.ui_mobile_other_apps to (androidIosApps + otherApps),
        )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        sections.forEach { (titleRes, sectionItems) ->
            if (sectionItems.isNotEmpty()) {
                item {
                    CatalogSection(
                        title = stringResource(titleRes),
                        items = sectionItems,
                        onProductClick = onProductClick,
                    )
                }
            }
        }
    }
}

@Composable
fun CatalogSection(
    title: String,
    items: List<PortfolioItem>,
    onProductClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(items) { item ->
                PortfolioItemTile(
                    item = item,
                    onClick = { onProductClick(item.repositoryUrl) },
                )
            }
        }
    }
}
