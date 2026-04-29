package dev.yuyuyuyuyu.portfolio.ui.components.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun PortfolioItemIcon(
    item: PortfolioItem,
    size: Dp = 124.dp,
    modifier: Modifier = Modifier
) {
    if (item is App) {
        Image(
            painter = painterResource(item.icon),
            contentDescription = "${item.name} icon",
            modifier = modifier
                .size(size)
                .clip(RoundedCornerShape(size / 6f)),
            contentScale = ContentScale.Crop,
        )
    } else {
        // Terminal Window Style Box (Gruvbox Light)
        Column(
            modifier = modifier
                .size(size)
                .clip(RoundedCornerShape(size / 8f))
                .background(Color(0xFFFBF1C7))
                .padding(size / 10f)
        ) {
            // macOS Window Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(size / 20f),
                modifier = Modifier.padding(bottom = size / 10f)
            ) {
                Box(modifier = Modifier.size(size / 12f).clip(CircleShape).background(Color(0xFFCC241D)))
                Box(modifier = Modifier.size(size / 12f).clip(CircleShape).background(Color(0xFFD79921)))
                Box(modifier = Modifier.size(size / 12f).clip(CircleShape).background(Color(0xFF98971A)))
            }
            
            // Terminal Prompt
            Text(
                text = "> ${item.name}",
                color = Color(0xFF3C3836),
                fontSize = (size.value / 8f).sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                lineHeight = (size.value / 6f).sp
            )
        }
    }
}
