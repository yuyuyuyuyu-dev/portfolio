package dev.yuyuyuyuyu.portfolio.ui.portfolio.search

import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem

/**
 * A [PortfolioItem] paired with its display strings already resolved from
 * string resources. Resolving requires a @Composable context, so the caller
 * does it; [filterResolvedItems] stays pure to keep the search logic
 * unit-testable without the Compose runtime.
 */
data class ResolvedPortfolioItem(
    val item: PortfolioItem,
    val name: String,
    val description: String,
)
