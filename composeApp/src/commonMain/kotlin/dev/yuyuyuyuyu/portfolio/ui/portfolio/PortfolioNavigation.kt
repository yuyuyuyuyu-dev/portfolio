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
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                PortfolioRoute.Today -> NavEntry(key) {
                    TodayScreen()
                }

                PortfolioRoute.Catalog -> NavEntry(key) {
                    CatalogScreen(
                        appsViewModel = appsViewModel,
                        librariesViewModel = librariesViewModel,
                        pluginsViewModel = pluginsViewModel,
                        cliToolsViewModel = cliToolsViewModel,
                        templatesViewModel = templatesViewModel,
                    )
                }

                PortfolioRoute.Search -> NavEntry(key) {
                    SearchScreen()
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
