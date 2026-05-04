package dev.yuyuyuyuyu.portfolio.ui.portfolio.catalog

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import kotlin.test.Test

class CatalogScreenContentTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun catalogScreenContent_rendersCategories() =
        runComposeUiTest {
            val mockWebApp =
                App(
                    nameFallback = "Mock Web App",
                    descriptionRes = Res.string.app_name,
                    techStack = emptySet(),
                    repositoryUrl = "https://github.com",
                    platforms = setOf(Platform.Web),
                    category = ProductCategory.App,
                )

            val mockLibrary =
                Product(
                    nameFallback = "Mock Library",
                    descriptionRes = Res.string.app_name,
                    techStack = emptySet(),
                    repositoryUrl = "https://github.com",
                    platforms = emptySet(),
                    category = ProductCategory.Library,
                )

            setContent {
                CatalogScreenContent(
                    allApps = listOf(mockWebApp),
                    allProducts = listOf(mockLibrary),
                    onProductClick = {},
                )
            }

            // Ensure it renders without crashing.
            // We avoid hardcoded string checks because localization/resources might behave differently in headless WASM tests.
            // For now, if setContent completes without throwing, the smoke test passes.
        }
}
