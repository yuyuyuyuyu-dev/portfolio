package dev.yuyuyuyuyu.portfolio.ui.portfolio.apps

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.AppListItem
import me.tatarka.inject.annotations.Inject

@Inject
@Composable
fun AppsScreen(viewModel: AppsViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier) {
        items(items = uiState.apps, key = { app -> app.name }) { app ->
            AppListItem(app)
        }
    }
}

@Preview
@Composable
private fun AppsScreenPreview() {
    AppsScreen(TODO())
}
