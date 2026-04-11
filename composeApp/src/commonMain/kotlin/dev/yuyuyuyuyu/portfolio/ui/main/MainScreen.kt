package dev.yuyuyuyuyu.portfolio.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import dev.yuyuyuyuyu.portfolio.di.AppComponent
import dev.yuyuyuyuyu.portfolio.di.create
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar

@Composable
fun MainScreen(appComponent: AppComponent) {
    val backStack: MutableList<MainNavigationRoute> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(MainNavigationRoute.Portfolio)
        }

    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = "portfolio",
                navigateBackIsPossible = backStack.size > 1,
                onNavigateBackButtonClick = { backStack.removeLastOrNull() },
                onOpenSourceLicensesButtonClick = {
                    if (backStack.lastOrNull() != MainNavigationRoute.Licenses) {
                        backStack.add(MainNavigationRoute.Licenses)
                    }
                },
                onSourceCodeButtonClick = {
                    uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/portfolio")
                },
            )
        },
    ) { innerPadding ->
        MainNavigation(
            backStack = backStack,
            appComponent = appComponent,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(appComponent = AppComponent::class.create())
}
