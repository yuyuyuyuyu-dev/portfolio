package dev.yuyuyuyuyu.portfolio.ui.portfolio.search

import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchFilterTest {
    @Test
    fun blankQuery_withoutFacets_returnsEveryItem() {
        val items = listOf(resolved("Alpha"), resolved("Beta"))

        val result = filterResolvedItems(items, query = "", category = null, platform = null)

        assertEquals(listOf("Alpha", "Beta"), result.names())
    }

    @Test
    fun query_matchesNameCaseInsensitively() {
        val items = listOf(resolved("Alpha"), resolved("Beta"))

        val result = filterResolvedItems(items, query = "alp", category = null, platform = null)

        assertEquals(listOf("Alpha"), result.names())
    }

    @Test
    fun query_matchesDescription() {
        val items =
            listOf(
                resolved("Alpha", description = "a fast CLI tool"),
                resolved("Beta", description = "a slow GUI app"),
            )

        val result = filterResolvedItems(items, query = "fast", category = null, platform = null)

        assertEquals(listOf("Alpha"), result.names())
    }

    @Test
    fun query_withNoMatch_returnsEmpty() {
        val items = listOf(resolved("Alpha"), resolved("Beta"))

        val result = filterResolvedItems(items, query = "zzz", category = null, platform = null)

        assertTrue(result.isEmpty())
    }

    @Test
    fun categoryFacet_keepsOnlyMatchingCategory() {
        val items =
            listOf(
                resolved("Alpha", category = ProductCategory.App),
                resolved("Beta", category = ProductCategory.Library),
            )

        val result = filterResolvedItems(items, query = "", category = ProductCategory.Library, platform = null)

        assertEquals(listOf("Beta"), result.names())
    }

    @Test
    fun platformFacet_keepsOnlyItemsSupportingThatPlatform() {
        val items =
            listOf(
                resolved("Alpha", platforms = setOf(Platform.Web)),
                resolved("Beta", platforms = setOf(Platform.Android)),
            )

        val result = filterResolvedItems(items, query = "", category = null, platform = Platform.Web)

        assertEquals(listOf("Alpha"), result.names())
    }

    @Test
    fun queryAndFacets_combineWithAndSemantics() {
        val items =
            listOf(
                resolved("Web App", category = ProductCategory.App, platforms = setOf(Platform.Web)),
                resolved("Web Lib", category = ProductCategory.Library, platforms = setOf(Platform.Web)),
                resolved("Android App", category = ProductCategory.App, platforms = setOf(Platform.Android)),
            )

        val result =
            filterResolvedItems(
                items,
                query = "web",
                category = ProductCategory.App,
                platform = Platform.Web,
            )

        assertEquals(listOf("Web App"), result.names())
    }

    @Test
    fun result_preservesInputOrder() {
        val items = listOf(resolved("Gamma"), resolved("Alpha"), resolved("Beta"))

        val result = filterResolvedItems(items, query = "", category = null, platform = null)

        assertEquals(listOf("Gamma", "Alpha", "Beta"), result.names())
    }

    private fun List<PortfolioItem>.names(): List<String?> = map { it.nameFallback }

    private fun resolved(
        name: String,
        description: String = "",
        category: ProductCategory = ProductCategory.App,
        platforms: Set<Platform> = emptySet(),
    ): ResolvedPortfolioItem =
        ResolvedPortfolioItem(
            item =
                App(
                    nameFallback = name,
                    descriptionRes = Res.string.app_name,
                    techStack = emptySet(),
                    repositoryUrl = "https://github.com",
                    platforms = platforms,
                    category = category,
                ),
            name = name,
            description = description,
        )
}
