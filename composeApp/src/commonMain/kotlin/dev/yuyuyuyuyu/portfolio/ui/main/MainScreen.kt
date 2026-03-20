package dev.yuyuyuyuyu.portfolio.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MainScreen() {
    Scaffold { innerPadding ->
        MainNavigation(modifier = Modifier.padding(innerPadding))
    }
}
