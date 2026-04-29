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
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModel
import org.jetbrains.compose.resources.painterResource

// Definition of Search Item Wrapper
private data class SearchItemData(
    val name: String,
    val description: String,
    val platforms: Set<Platform>,
    val repositoryUrl: String,
    val type: ItemCategory,
    val iconContent: @Composable () -> Unit
)

private enum class ItemCategory(val label: String) {
    App("アプリ"),
    CliTool("CLIツール"),
    Plugin("プラグイン"),
    Library("ライブラリ"),
    Template("テンプレート")
}

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
    var selectedCategory by remember { mutableStateOf<ItemCategory?>(null) }
    var selectedPlatform by remember { mutableStateOf<Platform?>(null) }

    // Map all items to a common data structure
    val allItems = remember(appsState, librariesState, pluginsState, cliToolsState, templatesState) {
        val mappedApps = appsState.apps.map { app ->
            SearchItemData(
                name = app.name,
                description = app.description,
                platforms = app.platforms,
                repositoryUrl = app.repositoryUrl,
                type = ItemCategory.App,
                iconContent = {
                    Image(
                        painter = painterResource(app.icon),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp).clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            )
        }

        val mapProduct = { product: Product, type: ItemCategory, defaultIcon: androidx.compose.ui.graphics.vector.ImageVector ->
            SearchItemData(
                name = product.name,
                description = product.description,
                platforms = product.platforms,
                repositoryUrl = product.repositoryUrl,
                type = type,
                iconContent = {
                    Column(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFBF1C7)) // Gruvbox Light background
                            .padding(4.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            modifier = Modifier.padding(bottom = 4.dp)
                        ) {
                            Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFFCC241D)))
                            Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFFD79921)))
                            Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(Color(0xFF98971A)))
                        }
                        Text(
                            text = ">",
                            color = Color(0xFF3C3836), // Gruvbox Light text
                            fontSize = 10.sp,
                            lineHeight = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }

        val mappedLibraries = librariesState.libraries.map { mapProduct(it, ItemCategory.Library, Icons.Default.Book) }
        val mappedPlugins = pluginsState.plugins.map { mapProduct(it, ItemCategory.Plugin, Icons.Default.Bolt) }
        val mappedCliTools = cliToolsState.cliTools.map { mapProduct(it, ItemCategory.CliTool, Icons.Default.Terminal) }
        val mappedTemplates = templatesState.templates.map { mapProduct(it, ItemCategory.Template, Icons.Default.Brush) }

        (mappedApps + mappedLibraries + mappedPlugins + mappedCliTools + mappedTemplates).sortedBy { it.name }
    }

    // Filter logic
    val filteredItems = allItems.filter { item ->
        val matchesQuery = if (searchQuery.isBlank()) true else {
            item.name.contains(searchQuery, ignoreCase = true) || item.description.contains(searchQuery, ignoreCase = true)
        }
        val matchesCategory = if (selectedCategory == null) true else item.type == selectedCategory
        val matchesPlatform = if (selectedPlatform == null) true else item.platforms.contains(selectedPlatform)

        matchesQuery && matchesCategory && matchesPlatform
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text("アプリ、ツール、プラグインを検索") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear")
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
            items(ItemCategory.entries.toTypedArray()) { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = if (selectedCategory == category) null else category },
                    label = { Text(category.label) }
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
                        Text("見つかりませんでした", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
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
    item: SearchItemData,
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
        item.iconContent()
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                maxLines = 1
            )
            Text(
                text = item.description,
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
                text = item.type.label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}
