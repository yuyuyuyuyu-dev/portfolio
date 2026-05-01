package dev.yuyuyuyuyu.portfolio.ui.components.listItems

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import dev.yuyuyuyuyu.portfolio.data.models.App
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import kotlin.test.Test

class PortfolioItemTileTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun portfolioItemTile_displaysAppName() = runComposeUiTest {
        val mockItem = App(
            nameFallback = "Test Portfolio App",
            descriptionRes = Res.string.app_name,
            techStack = emptySet(),
            repositoryUrl = "https://github.com",
            platforms = emptySet(),
            url = "https://example.com"
        )

        setContent {
            PortfolioItemTile(
                item = mockItem,
                onClick = {}
            )
        }

        onNodeWithText("Test Portfolio App").assertIsDisplayed()
    }
}
