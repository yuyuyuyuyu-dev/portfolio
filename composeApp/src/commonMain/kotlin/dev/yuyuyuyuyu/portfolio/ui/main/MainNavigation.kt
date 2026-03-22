package dev.yuyuyuyuyu.portfolio.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import dev.yuyuyuyuyu.portfolio.di.AppComponent
import dev.yuyuyuyuyu.portfolio.di.create
import dev.yuyuyuyuyu.portfolio.ui.licenses.LicensesScreen

@Composable
fun MainNavigation(appComponent: AppComponent, modifier: Modifier = Modifier) {
    val backStack: MutableList<MainNavigationRoute> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(MainNavigationRoute.Portfolio)
        }

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                MainNavigationRoute.Portfolio -> NavEntry(key) {
                    appComponent.portfolioScreen {
                        backStack.add(MainNavigationRoute.Licenses)
                    }
                }

                MainNavigationRoute.Licenses -> NavEntry(key) {
                    LicensesScreen(
                        onNavigateToPortfolio = {
                            backStack.add(MainNavigationRoute.Portfolio)
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun MainNavigationPreview() {
    MainNavigation(appComponent = AppComponent::class.create())
}
