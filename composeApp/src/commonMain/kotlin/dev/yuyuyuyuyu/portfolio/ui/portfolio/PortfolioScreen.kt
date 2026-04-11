package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Terminal
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
) {
    val backStack: MutableList<PortfolioRoute> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(PortfolioRoute.Apps)
        }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = backStack.last() == PortfolioRoute.Apps,
                    onClick = { backStack.add(PortfolioRoute.Apps) },
                    icon = { Icon(Icons.Default.Apps, null) },
                    label = { Text("アプリ") },
                )
                NavigationBarItem(
                    selected = backStack.last() == PortfolioRoute.Libraries,
                    onClick = { backStack.add(PortfolioRoute.Libraries) },
                    icon = { Icon(Icons.Default.Book, null) },
                    label = { Text("ライブラリ") },
                )
                NavigationBarItem(
                    selected = backStack.last() == PortfolioRoute.Plugins,
                    onClick = { backStack.add(PortfolioRoute.Plugins) },
                    icon = { Icon(Icons.Default.Bolt, null) },
                    label = { Text("プラグイン") },
                )
                NavigationBarItem(
                    selected = backStack.last() == PortfolioRoute.CliTools,
                    onClick = { backStack.add(PortfolioRoute.CliTools) },
                    icon = { Icon(Icons.Default.Terminal, null) },
                    label = { Text("CLIツール") },
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
