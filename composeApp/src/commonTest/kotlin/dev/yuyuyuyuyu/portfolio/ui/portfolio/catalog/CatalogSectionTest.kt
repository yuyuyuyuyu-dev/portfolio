package dev.yuyuyuyuyu.portfolio.ui.portfolio.catalog

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.portfolio.data.models.App
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import kotlin.test.Test

class CatalogSectionTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun catalogSection_rendersTitleAndItems() = runComposeUiTest {
        val mockItem = App(
            nameFallback = "Mock App in Catalog",
            descriptionRes = Res.string.app_name,
            techStack = emptySet(),
            repositoryUrl = "https://github.com",
            platforms = emptySet(),
        )

        setContent {
            CatalogSection(
                title = "Test Section Title",
                items = listOf(mockItem),
                onProductClick = {}
            )
        }

        onNodeWithText("Test Section Title").assertIsDisplayed()
        onNodeWithText("Mock App in Catalog").assertIsDisplayed()
    }
}
