package dev.yuyuyuyuyu.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.portfolio.di.AppComponent
import dev.yuyuyuyuyu.portfolio.di.create
import dev.yuyuyuyuyu.portfolio.ui.main.MainScreen
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.*

@Composable
@Preview
fun App() {
    val appComponent = retain { AppComponent::class.create() }
    val appName = stringResource(Res.string.app_name)

    LaunchedEffect(appName) {
        setWindowTitle(appName)
    }

    MyMaterialTheme {
        MainScreen(appComponent = appComponent)
    }
}
