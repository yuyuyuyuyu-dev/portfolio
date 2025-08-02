package dev.yuyuyuyuyu.portfolio.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.portfolio.di.portfolioAppModule
import dev.yuyuyuyuyu.portfolio.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioScreen
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject

@Composable
fun PortfolioApp() {
    val backStack = rememberSaveableBackStack(root = PortfolioScreen)
    val navigator = rememberCircuitNavigator(backStack) {}

    val uriHandler = LocalUriHandler.current

    KoinApplication(application = {
        printLogger()
        modules(portfolioAppModule)
    }) {
        MyMaterialTheme {
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
