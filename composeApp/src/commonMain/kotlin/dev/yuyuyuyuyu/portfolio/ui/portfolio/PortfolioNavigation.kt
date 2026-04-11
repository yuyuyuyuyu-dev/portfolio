package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModel

@Composable
fun PortfolioNavigation(
    backStack: MutableList<PortfolioRoute>,
    appsViewModel: AppsViewModel,
    librariesViewModel: LibrariesViewModel,
    pluginsViewModel: PluginsViewModel,
    cliToolsViewModel: CliToolsViewModel,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                PortfolioRoute.Apps -> NavEntry(key) {
                    AppsScreen(appsViewModel)
                }

                PortfolioRoute.Libraries -> NavEntry(key) {
                    LibrariesScreen(librariesViewModel)
                }

                PortfolioRoute.Plugins -> NavEntry(key) {
                    PluginsScreen(pluginsViewModel)
                }

                PortfolioRoute.CliTools -> NavEntry(key) {
                    CliToolsScreen(cliToolsViewModel)
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
