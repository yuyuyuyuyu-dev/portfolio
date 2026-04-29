package dev.yuyuyuyuyu.portfolio.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dev.yuyuyuyuyu.portfolio.di.AppComponent
import dev.yuyuyuyuyu.portfolio.di.create
import dev.yuyuyuyuyu.portfolio.ui.licenses.LicensesScreen

@Composable
fun MainNavigation(
    backStack: MutableList<MainNavigationRoute>,
    appComponent: AppComponent,
    modifier: Modifier = Modifier,
) {

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
                        onNavigateBack = { backStack.removeLastOrNull() }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun MainNavigationPreview() {
    MainNavigation(
        backStack = mutableListOf(MainNavigationRoute.Portfolio),
        appComponent = AppComponent::class.create()
    )
}
