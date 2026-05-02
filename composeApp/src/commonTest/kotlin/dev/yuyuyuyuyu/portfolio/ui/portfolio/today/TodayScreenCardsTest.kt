package dev.yuyuyuyuyu.portfolio.ui.portfolio.today

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import kotlin.test.Test

class TodayScreenCardsTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun toolOfTheDayCard_rendersCorrectly() =
        runComposeUiTest {
            val mockProduct =
                Product(
                    nameFallback = "Mock Tool of the Day",
                    descriptionRes = Res.string.app_name,
                    techStack = emptySet(),
                    repositoryUrl = "https://github.com",
                    platforms = emptySet(),
                    category = ProductCategory.CliTool,
                )

            setContent {
                ToolOfTheDayCard(
                    product = mockProduct,
                    title = "Awesome Tool Category",
                    onClick = {},
                )
            }

            onNodeWithText("Awesome Tool Category").assertIsDisplayed()
            onNodeWithText("Mock Tool of the Day").assertIsDisplayed()
        }
}
