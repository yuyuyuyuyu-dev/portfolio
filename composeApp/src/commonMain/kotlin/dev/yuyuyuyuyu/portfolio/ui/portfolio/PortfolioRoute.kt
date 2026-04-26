package dev.yuyuyuyuyu.portfolio.ui.portfolio

import kotlinx.serialization.Serializable

@Serializable
sealed interface PortfolioRoute {
    @Serializable
    data object Today : PortfolioRoute

    @Serializable
    data object Catalog : PortfolioRoute

    @Serializable
    data object Search : PortfolioRoute

    @Serializable
    data class Detail(val repositoryUrl: String) : PortfolioRoute
}
