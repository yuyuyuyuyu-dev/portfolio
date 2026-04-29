package dev.yuyuyuyuyu.portfolio.ui.components.listItems

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yuyuyuyuyu.portfolio.data.models.Product

@Composable
fun ProductTile(
    product: Product,
    icon: ImageVector,
    iconDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .clickable { onClick() }
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Terminal Window Style Box
        Column(
            modifier = Modifier
                .size(124.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFBF1C7)) // Gruvbox Light background
                .padding(8.dp)
        ) {
            // macOS Window Buttons (Red, Yellow, Green in Gruvbox Light)
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFCC241D)))
                Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFFD79921)))
                Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(Color(0xFF98971A)))
            }

            // Terminal Prompt / Code Snippet
            Text(
                text = "> ${product.name}",
                color = Color(0xFF3C3836), // Gruvbox Light text color
                fontSize = 14.sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp
            )
        }

        Column {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.height(32.dp)
            )
        }
    }
}
