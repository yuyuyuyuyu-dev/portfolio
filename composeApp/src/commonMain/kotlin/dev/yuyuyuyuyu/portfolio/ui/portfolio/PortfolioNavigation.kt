package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dev.yuyuyuyuyu.portfolio.ui.portfolio.catalog.CatalogScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.detail.DetailScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.search.SearchScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.today.TodayScreen

@Composable
fun PortfolioNavigation(
    backStack: MutableList<PortfolioRoute>,
    viewModels: PortfolioViewModels,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                PortfolioRoute.Today ->
                    NavEntry(key) {
                        TodayScreen(
                            viewModels = viewModels,
                            onProductClick = { url -> backStack.add(PortfolioRoute.Detail(url)) },
                        )
                    }

                PortfolioRoute.Catalog ->
                    NavEntry(key) {
                        CatalogScreen(
                            viewModels = viewModels,
                            onProductClick = { url -> backStack.add(PortfolioRoute.Detail(url)) },
                        )
                    }

                PortfolioRoute.Search ->
                    NavEntry(key) {
                        SearchScreen(
                            viewModels = viewModels,
                            onProductClick = { url -> backStack.add(PortfolioRoute.Detail(url)) },
                        )
                    }

                is PortfolioRoute.Detail ->
                    NavEntry(key) {
                        DetailEntry(
                            viewModels = viewModels,
                            repositoryUrl = key.repositoryUrl,
                        )
                    }
            }
        },
    )
}

@Composable
private fun DetailEntry(
    viewModels: PortfolioViewModels,
    repositoryUrl: String,
) {
    val appsState by viewModels.apps.uiState.collectAsState()
    val librariesState by viewModels.libraries.uiState.collectAsState()
    val pluginsState by viewModels.plugins.uiState.collectAsState()
    val cliToolsState by viewModels.cliTools.uiState.collectAsState()
    val templatesState by viewModels.templates.uiState.collectAsState()

    val allItems =
        appsState.apps + librariesState.libraries + pluginsState.plugins +
            cliToolsState.cliTools + templatesState.templates
    val item = allItems.find { it.repositoryUrl == repositoryUrl }

    if (item != null) {
        DetailScreen(item = item)
    } else {
        // Fallback or error state
        Text("Item not found")
    }
}

@Preview
@Composable
private fun PortfolioNavigationPreview() {
    TODO()
}
