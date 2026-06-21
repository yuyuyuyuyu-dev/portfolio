package dev.yuyuyuyuyu.portfolio.ui.portfolio.search

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.v2.runComposeUiTest
import dev.yuyuyuyuyu.portfolio.data.repositories.AppsRepository
import dev.yuyuyuyuyu.portfolio.data.repositories.CliToolsRepository
import dev.yuyuyuyuyu.portfolio.data.repositories.LibrariesRepository
import dev.yuyuyuyuyu.portfolio.data.repositories.PluginsRepository
import dev.yuyuyuyuyu.portfolio.data.repositories.TemplatesRepository
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioViewModels
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModelImpl
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModelImpl
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModelImpl
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModelImpl
import dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModelImpl
import kotlin.test.Test

/**
 * Exercises the full SearchScreen wiring with no mocks: real repositories feed
 * real view models, which the screen merges and renders. Each test types a
 * substring of a known item's name to narrow the results to that single item,
 * which sidesteps lazy-list virtualization and avoids colliding with the query
 * text shown in the search field (the typed substring never equals the
 * asserted full name).
 */
class SearchScreenWiringTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun searchScreen_surfacesAnAppFromRealViewModels() =
        runComposeUiTest {
            val viewModels = realViewModels()

            setContent {
                SearchScreen(viewModels = viewModels, onProductClick = {})
            }

            onNode(hasSetTextAction()).performTextInput("PureMusic")

            onNodeWithText("PureMusicPlayer").assertIsDisplayed()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun searchScreen_surfacesACliToolFromRealViewModels() =
        runComposeUiTest {
            val viewModels = realViewModels()

            setContent {
                SearchScreen(viewModels = viewModels, onProductClick = {})
            }

            onNode(hasSetTextAction()).performTextInput("html2pdf")

            onNodeWithText("@yuyuyuyuyu-dev/html2pdf").assertIsDisplayed()
        }

    private fun realViewModels(): PortfolioViewModels =
        PortfolioViewModels(
            apps = AppsViewModelImpl(AppsRepository()),
            libraries = LibrariesViewModelImpl(LibrariesRepository()),
            plugins = PluginsViewModelImpl(PluginsRepository()),
            cliTools = CliToolsViewModelImpl(CliToolsRepository()),
            templates = TemplatesViewModelImpl(TemplatesRepository()),
        )
}
