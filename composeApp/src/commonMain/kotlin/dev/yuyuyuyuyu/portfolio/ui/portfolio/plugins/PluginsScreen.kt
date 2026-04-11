package dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.ProductListItem

@Composable
fun PluginsScreen(viewModel: PluginsViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier) {
        items(items = uiState.plugins, key = { plugin -> plugin.name }) { plugin ->
            ProductListItem(
                product = plugin,
                icon = Icons.Default.Bolt,
                iconDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun PluginsScreenPreview() {
    PluginsScreen(TODO())
}
