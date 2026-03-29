package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsScreen

@Composable
fun PortfolioNavigation(
    backStack: MutableList<PortfolioRoute>,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                PortfolioRoute.Apps -> NavEntry(key) {
                    AppsScreen()
                }

                PortfolioRoute.Libraries -> NavEntry(key) {
                    LibrariesScreen()
                }

                PortfolioRoute.Plugins -> NavEntry(key) {
                    PluginsScreen()
                }

                PortfolioRoute.CliTools -> NavEntry(key) {
                    CliToolsScreen()
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
