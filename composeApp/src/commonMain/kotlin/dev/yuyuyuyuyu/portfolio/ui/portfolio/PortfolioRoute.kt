package dev.yuyuyuyuyu.portfolio.ui.portfolio

import kotlinx.serialization.Serializable

@Serializable
sealed interface PortfolioRoute {
    @Serializable
    data object Apps : PortfolioRoute

    @Serializable
    data object Libraries : PortfolioRoute

    @Serializable
    data object Plugins : PortfolioRoute

    @Serializable
    data object CliTools : PortfolioRoute
}
