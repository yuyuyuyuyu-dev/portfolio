package dev.yuyuyuyuyu.portfolio.ui.portfolio.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.*

import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.PortfolioItemIcon

import dev.yuyuyuyuyu.portfolio.utils.displayName
import dev.yuyuyuyuyu.portfolio.utils.displayDescription

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    appsViewModel: AppsViewModel,
    librariesViewModel: LibrariesViewModel,
    pluginsViewModel: PluginsViewModel,
    cliToolsViewModel: CliToolsViewModel,
    templatesViewModel: TemplatesViewModel,
    onProductClick: (String) -> Unit,
) {
    val appsState by appsViewModel.uiState.collectAsState()
    val librariesState by librariesViewModel.uiState.collectAsState()
    val pluginsState by pluginsViewModel.uiState.collectAsState()
    val cliToolsState by cliToolsViewModel.uiState.collectAsState()
    val templatesState by templatesViewModel.uiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<ProductCategory?>(null) }
    var selectedPlatform by remember { mutableStateOf<Platform?>(null) }

    // Map all items to a common data structure (PortfolioItem)
    val allItems = remember(appsState, librariesState, pluginsState, cliToolsState, templatesState) {
        val items = appsState.apps + librariesState.libraries + pluginsState.plugins + cliToolsState.cliTools + templatesState.templates
        items.sortedBy { it.nameFallback }
    }

    // Resolve strings for filtering
    val itemWithStrings = allItems.map { item ->
        val resolvedName = item.displayName
        val resolvedDesc = item.displayDescription
        Triple<PortfolioItem, String, String>(item, resolvedName, resolvedDesc)
    }

    // Filter logic
    val filteredItems = itemWithStrings.filter { triple ->
        val item = triple.first
        val name = triple.second
        val description = triple.third
        val matchesQuery = if (searchQuery.isBlank()) true else {
            name.contains(searchQuery, ignoreCase = true) || description.contains(searchQuery, ignoreCase = true)
        }
        val matchesCategory = if (selectedCategory == null) true else item.category == selectedCategory
        val matchesPlatform = if (selectedPlatform == null) true else item.platforms.contains(selectedPlatform)

        matchesQuery && matchesCategory && matchesPlatform
    }.map { it.first }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text(stringResource(Res.string.ui_search_placeholder)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = stringResource(Res.string.ui_search)) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = stringResource(Res.string.ui_clear))
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(100) // Pill shape
        )

        // Filters (Categories & Platforms)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Category filters
            items(ProductCategory.entries.toTypedArray()) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = if (selectedCategory == category) null else category },
                    label = { Text(stringResource(category.labelRes)) }
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
                    onClick = { selectedPlatform = if (selectedPlatform == platform) null else platform },
                    label = { Text(platform.label) }
                )
            }
        }

        HorizontalDivider()

        // Results List
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (filteredItems.isEmpty()) {
                item {
                    Box(modifier = Modifier.fillMaxWidth().padding(top = 64.dp), contentAlignment = Alignment.Center) {
                        Text(stringResource(Res.string.ui_no_results), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            } else {
                items(filteredItems) { item ->
                    SearchResultItem(item = item, onClick = { onProductClick(item.repositoryUrl) })
                }
            }
        }
    }
}

@Composable
private fun SearchResultItem(
    item: PortfolioItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PortfolioItemIcon(item = item, size = 48.dp)
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.displayName,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                maxLines = 1
            )
            Text(
                text = item.displayDescription,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2
            )
        }
        
        // Category Label
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = RoundedCornerShape(100),
            modifier = Modifier.align(Alignment.Top)
        ) {
            Text(
                text = stringResource(item.category.labelRes),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}
