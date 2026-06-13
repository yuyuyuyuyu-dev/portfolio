package dev.yuyuyuyuyu.portfolio.ui.portfolio.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.PortfolioItemIcon
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioViewModels
import dev.yuyuyuyuyu.portfolio.utils.displayDescription
import dev.yuyuyuyuyu.portfolio.utils.displayName
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ui_clear
import portfolio.composeapp.generated.resources.ui_no_results
import portfolio.composeapp.generated.resources.ui_search
import portfolio.composeapp.generated.resources.ui_search_placeholder

@Composable
fun SearchScreen(
    viewModels: PortfolioViewModels,
    onProductClick: (String) -> Unit,
) {
    val appsState by viewModels.apps.uiState.collectAsState()
    val librariesState by viewModels.libraries.uiState.collectAsState()
    val pluginsState by viewModels.plugins.uiState.collectAsState()
    val cliToolsState by viewModels.cliTools.uiState.collectAsState()
    val templatesState by viewModels.templates.uiState.collectAsState()

    // Map all items to a common data structure (PortfolioItem)
    val allItems =
        remember(appsState, librariesState, pluginsState, cliToolsState, templatesState) {
            val items = appsState.apps + librariesState.libraries + pluginsState.plugins + cliToolsState.cliTools + templatesState.templates
            items.sortedBy { it.nameFallback }
        }

    SearchScreenContent(
        allItems = allItems,
        onProductClick = onProductClick,
    )
}

@Composable
fun SearchScreenContent(
    allItems: List<PortfolioItem>,
    onProductClick: (String) -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
    var selectedPlatform by remember { mutableStateOf<Platform?>(null) }

    // Resolve strings for filtering
    val itemWithStrings =
        allItems.map { item ->
            val resolvedName = item.displayName
            val resolvedDesc = item.displayDescription
            Triple<PortfolioItem, String, String>(item, resolvedName, resolvedDesc)
        }

    // Filter logic
    val filteredItems =
        itemWithStrings
            .filter { triple ->
                val item = triple.first
                val name = triple.second
                val description = triple.third
                val matchesQuery =
                    if (searchQuery.isBlank()) {
                        true
                    } else {
                        name.contains(searchQuery, ignoreCase = true) || description.contains(searchQuery, ignoreCase = true)
                    }
                val matchesCategory = if (selectedCategory == null) true else item.category == selectedCategory
                val matchesPlatform = if (selectedPlatform == null) true else item.platforms.contains(selectedPlatform)

                matchesQuery && matchesCategory && matchesPlatform
            }.map { it.first }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchQueryField(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
        )

        SearchFilterRow(
            selectedCategory = selectedCategory,
            onCategoryClick = { category ->
                selectedCategory = if (selectedCategory == category) null else category
            },
            selectedPlatform = selectedPlatform,
            onPlatformClick = { platform ->
                selectedPlatform = if (selectedPlatform == platform) null else platform
            },
        )

        HorizontalDivider()

        SearchResults(
            filteredItems = filteredItems,
            onProductClick = onProductClick,
        )
    }
}

@Composable
private fun SearchQueryField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        placeholder = { Text(stringResource(Res.string.ui_search_placeholder)) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = stringResource(Res.string.ui_search)) },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = stringResource(Res.string.ui_clear))
                }
            }
        },
        singleLine = true,
        // Pill shape
        shape = RoundedCornerShape(percent = 100),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchFilterRow(
    selectedCategory: ProductCategory?,
    onCategoryClick: (ProductCategory) -> Unit,
    selectedPlatform: Platform?,
    onPlatformClick: (Platform) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Category filters
        items(ProductCategory.entries.toTypedArray()) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onCategoryClick(category) },
                label = { Text(stringResource(category.labelRes)) },
            )
        }

        item {
            VerticalDivider(modifier = Modifier.height(24.dp).padding(horizontal = 8.dp))
        }

        // Important platform filters
        val commonPlatforms = listOf(Platform.MacOS, Platform.Web, Platform.Android, Platform.Ios)
        items(commonPlatforms) { platform ->
            FilterChip(
                selected = selectedPlatform == platform,
                onClick = { onPlatformClick(platform) },
                label = { Text(platform.label) },
            )
        }
    }
}

@Composable
private fun SearchResults(
    filteredItems: List<PortfolioItem>,
    onProductClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        if (filteredItems.isEmpty()) {
            item {
                Box(modifier = Modifier.fillMaxWidth().padding(top = 64.dp), contentAlignment = Alignment.Center) {
                    Text(
                        stringResource(Res.string.ui_no_results),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        } else {
            items(filteredItems) { item ->
                SearchResultItem(item = item, onClick = { onProductClick(item.repositoryUrl) })
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    item: PortfolioItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable { onClick() }
                .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        PortfolioItemIcon(item = item, size = 48.dp)

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.displayName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                maxLines = 1,
            )
            Text(
                text = item.displayDescription,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
            )
        }

        // Category Label
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(percent = 100),
            modifier = Modifier.align(Alignment.Top),
        ) {
            Text(
                text = stringResource(item.category.labelRes),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
    }
}
