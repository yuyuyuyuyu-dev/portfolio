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
import androidx.compose.ui.tooling.preview.Preview
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
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
) {
    val backStack: MutableList<PortfolioRoute> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(PortfolioRoute.Today)
        }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = backStack.last() == PortfolioRoute.Today,
                    onClick = { backStack.add(PortfolioRoute.Today) },
                    icon = { Icon(Icons.Default.Today, null) },
                    label = { Text("Today") },
                )
                NavigationBarItem(
                    selected = backStack.last() == PortfolioRoute.Catalog,
                    onClick = { backStack.add(PortfolioRoute.Catalog) },
                    icon = { Icon(Icons.Default.Apps, null) },
                    label = { Text("Apps / Tools") },
                )
                NavigationBarItem(
                    selected = backStack.last() == PortfolioRoute.Search,
                    onClick = { backStack.add(PortfolioRoute.Search) },
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
