package dev.yuyuyuyuyu.portfolio.ui.portfolio.detail

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.portfolio.data.models.App
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import kotlin.test.Test

class DetailScreenTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun detailScreen_rendersWithoutCrashing() = runComposeUiTest {
        val mockItem = App(
            nameFallback = "Test Detail App",
            descriptionRes = Res.string.app_name,
            techStack = emptySet(),
            repositoryUrl = "https://github.com",
            platforms = emptySet(),
            installCommand = "npm install test-app"
        )

        setContent {
            DetailScreen(item = mockItem)
        }

        onNodeWithText("Test Detail App").assertIsDisplayed()
        onNodeWithText("npm install test-app").assertIsDisplayed()
    }
}
