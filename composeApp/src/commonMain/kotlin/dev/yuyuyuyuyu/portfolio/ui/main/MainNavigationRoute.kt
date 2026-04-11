package dev.yuyuyuyuyu.portfolio.ui.main

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface MainNavigationRoute : NavKey {
    @Serializable
    data object Portfolio : MainNavigationRoute

    @Serializable
    data object Licenses : MainNavigationRoute
}
