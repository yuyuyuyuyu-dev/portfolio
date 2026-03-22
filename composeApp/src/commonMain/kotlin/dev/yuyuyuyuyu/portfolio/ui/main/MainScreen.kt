package dev.yuyuyuyuyu.portfolio.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.portfolio.di.AppComponent
import dev.yuyuyuyuyu.portfolio.di.create

@Composable
fun MainScreen(appComponent: AppComponent) {
    Scaffold { innerPadding ->
        MainNavigation(appComponent = appComponent, modifier = Modifier.padding(innerPadding))
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(appComponent = AppComponent::class.create())
}
