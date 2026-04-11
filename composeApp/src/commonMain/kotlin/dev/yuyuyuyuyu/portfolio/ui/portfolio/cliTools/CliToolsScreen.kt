package dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.ProductListItem

@Composable
fun CliToolsScreen(viewModel: CliToolsViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier) {
        items(items = uiState.cliTools, key = { cliTool -> cliTool.name }) { cliTool ->
            ProductListItem(
                product = cliTool,
                icon = Icons.Default.Terminal,
                iconDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun CliToolsScreenPreview() {
    CliToolsScreen(TODO())
}
