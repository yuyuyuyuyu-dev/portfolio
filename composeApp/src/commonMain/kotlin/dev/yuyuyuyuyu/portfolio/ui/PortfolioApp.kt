package dev.yuyuyuyuyu.portfolio.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.createtypography.createTypographyFrom
import dev.yuyuyuyuyu.portfolio.di.portfolioAppModule
import dev.yuyuyuyuyu.portfolio.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioScreen
import dev.yuyuyuyuyu.portfolio.ui.theme.darkScheme
import dev.yuyuyuyuyu.portfolio.ui.theme.lightScheme
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import org.jetbrains.compose.resources.Font
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.Yomogi_Regular

@Composable
fun PortfolioApp() {
    val backStack = rememberSaveableBackStack(root = PortfolioScreen)
    val navigator = rememberCircuitNavigator(backStack) {}

    val uriHandler = LocalUriHandler.current

    KoinApplication(application = {
        printLogger()
        modules(portfolioAppModule)
    }) {
        MaterialTheme(
            colorScheme = if (isSystemInDarkTheme()) darkScheme else lightScheme,
            typography = createTypographyFrom(FontFamily(Font(Res.font.Yomogi_Regular))),
        ) {
            Scaffold(
                topBar = {
                    SimpleTopAppBar(
                        title = "portfolio",
                        navigateBackIsPossible = backStack.size > 1,
                        onNavigateBackButtonClick = { navigator.pop() },
                        onOpenSourceLicensesButtonClick = {
                            navigator.goTo(OpenSourceLicenseListScreen)
                        },
                        onSourceCodeButtonClick = {
                            uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/portfolio")
                        },
                    )
                },
            ) { innerPadding ->
                CircuitCompositionLocals(koinInject()) {
                    NavigableCircuitContent(
                        navigator = navigator,
                        backStack = backStack,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
