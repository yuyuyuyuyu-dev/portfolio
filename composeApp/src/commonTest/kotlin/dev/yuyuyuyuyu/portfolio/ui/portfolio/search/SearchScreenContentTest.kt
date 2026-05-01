package dev.yuyuyuyuyu.portfolio.ui.portfolio.search

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import kotlin.test.Test

class SearchScreenContentTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun searchScreenContent_rendersWithoutCrashing() = runComposeUiTest {
        val mockApp = App(
            nameFallback = "Mock Search App",
            descriptionRes = Res.string.app_name,
            techStack = emptySet(),
            repositoryUrl = "https://github.com",
            platforms = emptySet(),
            category = ProductCategory.App
        )

        setContent {
            SearchScreenContent(
                allItems = listOf(mockApp),
                onProductClick = {}
            )
        }

        // Verify that the search result is rendered
        onNodeWithText("Mock Search App").assertIsDisplayed()
    }
}
