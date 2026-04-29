package dev.yuyuyuyuyu.portfolio.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.tooling.preview.Preview
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import dev.yuyuyuyuyu.portfolio.di.AppComponent
import dev.yuyuyuyuyu.portfolio.di.create

@Composable
fun MainScreen(appComponent: AppComponent) {
    val backStack: MutableList<MainNavigationRoute> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(MainNavigationRoute.Portfolio)
        }

    MainNavigation(
        backStack = backStack,
        appComponent = appComponent,
    )
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(appComponent = AppComponent::class.create())
}
