package dev.yuyuyuyuyu.portfolio.ui.portfolio.search

import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory

/**
 * Returns the items matching a free-text [query] (compared against name and
 * description, case-insensitively) and the optional [category] / [platform]
 * facets. A null facet means "no constraint" and a blank query matches
 * everything; all conditions are combined with AND. The input order is
 * preserved.
 */
fun filterResolvedItems(
    items: List<ResolvedPortfolioItem>,
    query: String,
    category: ProductCategory?,
    platform: Platform?,
): List<PortfolioItem> =
    items
        .filter { resolved ->
            val matchesQuery =
                if (query.isBlank()) {
                    true
                } else {
                    resolved.name.contains(query, ignoreCase = true) ||
                        resolved.description.contains(query, ignoreCase = true)
                }
            val matchesCategory = category == null || resolved.item.category == category
            val matchesPlatform = platform == null || resolved.item.platforms.contains(platform)

            matchesQuery && matchesCategory && matchesPlatform
        }.map { it.item }
