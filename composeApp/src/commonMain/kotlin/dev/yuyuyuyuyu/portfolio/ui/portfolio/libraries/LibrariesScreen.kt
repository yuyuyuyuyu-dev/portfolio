package dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.ProductListItem

@Composable
fun LibrariesScreen(viewModel: LibrariesViewModel, modifier: Modifier = Modifier) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(modifier) {
        items(items = uiState.libraries, key = { library -> library.name }) { library ->
            ProductListItem(
                product = library,
                icon = Icons.Default.Book,
                iconDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun LibrariesScreenPreview() {
    LibrariesScreen(TODO())
}
