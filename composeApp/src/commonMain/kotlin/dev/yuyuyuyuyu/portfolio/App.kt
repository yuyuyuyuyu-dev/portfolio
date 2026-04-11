package dev.yuyuyuyuyu.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.retain.retain
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.mymaterialtheme.MyMaterialTheme
import dev.yuyuyuyuyu.portfolio.di.AppComponent
import dev.yuyuyuyuyu.portfolio.di.create
import dev.yuyuyuyuyu.portfolio.ui.main.MainScreen

@Composable
@Preview
fun App() {
    val appComponent = retain { AppComponent::class.create() }

    MyMaterialTheme {
        MainScreen(appComponent = appComponent)
    }
}
