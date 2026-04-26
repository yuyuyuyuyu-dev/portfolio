package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.today.TodayScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.catalog.CatalogScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.search.SearchScreen

import dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.yuyuyuyuyu.portfolio.ui.portfolio.detail.DetailScreen

@Composable
fun PortfolioNavigation(
    backStack: MutableList<PortfolioRoute>,
    appsViewModel: AppsViewModel,
    librariesViewModel: LibrariesViewModel,
    pluginsViewModel: PluginsViewModel,
    cliToolsViewModel: CliToolsViewModel,
    templatesViewModel: TemplatesViewModel,
    modifier: Modifier = Modifier,
) {
    val appsState by appsViewModel.uiState.collectAsState()
    val librariesState by librariesViewModel.uiState.collectAsState()
    val pluginsState by pluginsViewModel.uiState.collectAsState()
    val cliToolsState by cliToolsViewModel.uiState.collectAsState()
    val templatesState by templatesViewModel.uiState.collectAsState()

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                PortfolioRoute.Today -> NavEntry(key) {
                    TodayScreen(
                        appsViewModel = appsViewModel,
                        pluginsViewModel = pluginsViewModel,
                        cliToolsViewModel = cliToolsViewModel,
                        templatesViewModel = templatesViewModel,
                        onProductClick = { url -> backStack.add(PortfolioRoute.Detail(url)) }
                    )
                }

                PortfolioRoute.Catalog -> NavEntry(key) {
                    CatalogScreen(
                        appsViewModel = appsViewModel,
                        librariesViewModel = librariesViewModel,
                        pluginsViewModel = pluginsViewModel,
                        cliToolsViewModel = cliToolsViewModel,
                        templatesViewModel = templatesViewModel,
                        onProductClick = { url -> backStack.add(PortfolioRoute.Detail(url)) }
                    )
                }

                PortfolioRoute.Search -> NavEntry(key) {
                    SearchScreen(
                        appsViewModel = appsViewModel,
                        librariesViewModel = librariesViewModel,
                        pluginsViewModel = pluginsViewModel,
                        cliToolsViewModel = cliToolsViewModel,
                        templatesViewModel = templatesViewModel,
                        onProductClick = { url -> backStack.add(PortfolioRoute.Detail(url)) }
                    )
                }

                is PortfolioRoute.Detail -> NavEntry(key) {
                    val url = key.repositoryUrl
                    val app = appsState.apps.find { it.repositoryUrl == url }
                    val product = sequenceOf(
                        librariesState.libraries,
                        pluginsState.plugins,
                        cliToolsState.cliTools,
                        templatesState.templates
                    ).flatten().find { it.repositoryUrl == url }

                    DetailScreen(app = app, product = product)
                }
            }
        },
    )
}

@Preview
@Composable
private fun PortfolioNavigationPreview() {
    TODO()
}
