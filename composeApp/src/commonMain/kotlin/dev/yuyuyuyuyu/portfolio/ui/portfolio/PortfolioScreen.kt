package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import me.tatarka.inject.annotations.Inject

typealias PortfolioScreen = @Composable (onNavigateToLicenses: () -> Unit) -> Unit

@Inject
@Composable
fun PortfolioScreen(
    appsViewModel: dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModelImpl,
    librariesViewModel: dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModelImpl,
    pluginsViewModel: dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModelImpl,
    cliToolsViewModel: dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModelImpl,
    templatesViewModel: dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModelImpl,
    @me.tatarka.inject.annotations.Assisted onNavigateToLicenses: () -> Unit
) {
    val backStack: MutableList<PortfolioRoute> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(PortfolioRoute.Today)
        }

    val currentRoute = backStack.lastOrNull()
    val isTopLevel = currentRoute == PortfolioRoute.Today || currentRoute == PortfolioRoute.Catalog || currentRoute == PortfolioRoute.Search
    val currentTab = if (isTopLevel) currentRoute else backStack.getOrNull(1) ?: PortfolioRoute.Today

    fun navigateToTab(route: PortfolioRoute) {
        if (currentTab == route) {
            // Pop to root of the current tab if already selected
            while (backStack.size > (if (route == PortfolioRoute.Today) 1 else 2)) {
                backStack.removeLastOrNull()
            }
            return
        }
        
        backStack.clear()
        backStack.add(PortfolioRoute.Today)
        if (route != PortfolioRoute.Today) {
            backStack.add(route)
        }
    }

    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = "portfolio",
                navigateBackIsPossible = !isTopLevel,
                onNavigateBackButtonClick = { backStack.removeLastOrNull() },
                onOpenSourceLicensesButtonClick = onNavigateToLicenses,
                onSourceCodeButtonClick = {
                    uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/portfolio")
                },
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentTab == PortfolioRoute.Today,
                    onClick = { navigateToTab(PortfolioRoute.Today) },
                    icon = { Icon(Icons.Default.Today, null) },
                    label = { Text("Today") },
                )
                NavigationBarItem(
                    selected = currentTab == PortfolioRoute.Catalog,
                    onClick = { navigateToTab(PortfolioRoute.Catalog) },
                    icon = { Icon(Icons.Default.Apps, null) },
                    label = { Text("Apps / Tools") },
                )
                NavigationBarItem(
                    selected = currentTab == PortfolioRoute.Search,
                    onClick = { navigateToTab(PortfolioRoute.Search) },
                    icon = { Icon(Icons.Default.Search, null) },
                    label = { Text("検索") },
                )
            }
        },
    ) { innerPadding ->
        PortfolioNavigation(
            backStack = backStack,
            appsViewModel = appsViewModel,
            librariesViewModel = librariesViewModel,
            pluginsViewModel = pluginsViewModel,
            cliToolsViewModel = cliToolsViewModel,
            templatesViewModel = templatesViewModel,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview
@Composable
private fun PortfolioScreenPreview() {
    // プレビュー用にモックやTODOを使用
    Text("Portfolio Screen Preview")
}
