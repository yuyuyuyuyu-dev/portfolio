package dev.yuyuyuyuyu.portfolio.ui.portfolio.detail

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalProvidableLocaleList
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.repositories.CliToolsRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.getString
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import portfolio.composeapp.generated.resources.ui_readme_load_failed
import kotlin.test.Test

class DetailScreenTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun detailScreen_rendersWithoutCrashing() =
        runComposeUiTest {
            val mockItem =
                App(
                    nameFallback = "Test Detail App",
                    descriptionRes = Res.string.app_name,
                    techStack = emptySet(),
                    repositoryUrl = "https://github.com",
                    platforms = emptySet(),
                    installCommand = "npm install test-app",
                )

            setContent {
                DetailScreen(item = mockItem)
            }

            onNodeWithText("Test Detail App").assertIsDisplayed()
            onNodeWithText("npm install test-app").assertIsDisplayed()
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun detailScreen_showsTheReadmeFromGitHubInEnglish() =
        runComposeUiTest {
            val kotlinstall = kotlinstall()
            val gitHub =
                MockEngine { request ->
                    if (request.url.toString() == kotlinstall.readme?.rawUrl) {
                        respond("# kotlinstall\n\nThe README on GitHub.")
                    } else {
                        respondError(HttpStatusCode.NotFound)
                    }
                }

            setContent {
                CompositionLocalProvider(
                    LocalProvidableLocaleList provides LocaleList(Locale("en")),
                    LocalHttpClient provides HttpClient(gitHub),
                ) {
                    DetailScreen(item = kotlinstall)
                }
            }

            awaitNode(hasText("The README on GitHub."))
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun detailScreen_showsTheJapaneseReadmeInJapanese() =
        runComposeUiTest {
            val kotlinstall = kotlinstall()

            setContent {
                CompositionLocalProvider(LocalProvidableLocaleList provides LocaleList(Locale("ja"))) {
                    DetailScreen(item = kotlinstall)
                }
            }

            awaitNode(hasText("それでもKotlinが好きで諦めきれなかったので、kotlinstallを作りました。"))
        }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun detailScreen_showsAnErrorWhenTheReadmeCannotBeLoaded() =
        runComposeUiTest {
            val kotlinstall = kotlinstall()
            val gitHub = MockEngine { respondError(HttpStatusCode.InternalServerError) }

            setContent {
                CompositionLocalProvider(
                    LocalProvidableLocaleList provides LocaleList(Locale("en")),
                    LocalHttpClient provides HttpClient(gitHub),
                ) {
                    DetailScreen(item = kotlinstall)
                }
            }

            awaitNode(hasText(getString(Res.string.ui_readme_load_failed)))
        }
}

private fun kotlinstall() = CliToolsRepository().getCliTools().first { it.nameFallback == "kotlinstall" }

@OptIn(ExperimentalTestApi::class)
private suspend fun ComposeUiTest.awaitNode(matcher: SemanticsMatcher) {
    repeat(100) {
        if (onAllNodes(matcher).fetchSemanticsNodes().isNotEmpty()) return
        withContext(Dispatchers.Default) { delay(100) }
    }
    onNode(matcher).assertExists()
}
