package dev.yuyuyuyuyu.portfolio.ui.portfolio.today

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import kotlin.test.Test

class TodayScreenContentTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun todayScreenContent_rendersWithoutCrashing() =
        runComposeUiTest {
            val mockApp =
                App(
                    nameFallback = "Mock App",
                    descriptionRes = Res.string.app_name,
                    techStack = emptySet(),
                    repositoryUrl = "https://github.com",
                    platforms = emptySet(),
                    category = ProductCategory.App,
                )

            setContent {
                TodayScreenContent(
                    featuredItems = TodayFeaturedItems(howOldAmI = mockApp),
                    onProductClick = {},
                    onLinkClick = {},
                )
            }

            // Header Check
            onNodeWithText("Today").assertIsDisplayed()

            // Ensure the list renders without crash. Items lower down might not be in the hierarchy yet due to LazyColumn lazy-loading.
            // It's sufficient to check the top visible items for a smoke test.
        }
}
