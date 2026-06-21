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
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.PortfolioItemTile
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioViewModels
import org.jetbrains.compose.resources.stringResource

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
    val sections = catalogSections(allApps, allProducts)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        sections.forEach { (titleRes, sectionItems) ->
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
