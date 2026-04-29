package dev.yuyuyuyuyu.portfolio.ui.components.listItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.shared.BaseListItem
import dev.yuyuyuyuyu.portfolio.ui.components.listItems.shared.ItemBody

@Composable
fun ProductListItem(
    product: Product,
    icon: ImageVector,
    iconDescription: String?,
    modifier: Modifier = Modifier,
) = BaseListItem(
    name = product.name,
    description = product.description,
    techStackSet = product.techStack,
    icon = {
        Box(
            modifier = it,
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = iconDescription,
                modifier = Modifier.size(30.dp),
            )
        }
    },
    modifier = modifier,
) { uriHandler ->
    ItemBody(
        repositoryUrl = product.repositoryUrl,
        sourceCodeLabel = "GitHub",
        onSourceCodeLinkClick = { uriHandler.openUri(product.repositoryUrl) },
    )
}

@Preview
@Composable
private fun ProductListItemPreview() {
    ProductListItem(
        product = Product(
            name = "Sample Product",
            description = "This is a sample product description.",
            techStack = setOf(TechStack.Kotlin, TechStack.JetpackCompose),
            repositoryUrl = "https://github.com/example/repo",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
            platforms = emptySet(),
        ),
        icon = Icons.Default.Star,
        iconDescription = "Star",
    )
}
