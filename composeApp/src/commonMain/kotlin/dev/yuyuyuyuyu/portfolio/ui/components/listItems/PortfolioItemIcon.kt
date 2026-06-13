package dev.yuyuyuyuyu.portfolio.ui.components.listItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import dev.yuyuyuyuyu.portfolio.utils.displayName
import org.jetbrains.compose.resources.painterResource

// Gruvbox Light palette for the terminal-window style box
private val TerminalBackgroundColor = Color(0xFFFBF1C7)
private val TerminalPromptColor = Color(0xFF3C3836)
private val WindowCloseButtonColor = Color(0xFFCC241D)
private val WindowMinimizeButtonColor = Color(0xFFD79921)
private val WindowZoomButtonColor = Color(0xFF98971A)

// Dimensions expressed as fractions of the icon size
private const val AppCornerRatio = 6f
private const val TerminalCornerRatio = 8f
private const val TerminalPaddingRatio = 10f
private const val WindowButtonSpacingRatio = 20f
private const val WindowButtonSizeRatio = 12f
private const val PromptFontRatio = 8f
private const val PromptLineHeightRatio = 6f

@Composable
fun PortfolioItemIcon(
    item: PortfolioItem,
    size: Dp = 124.dp,
    modifier: Modifier = Modifier,
) {
    if (item is App) {
        Image(
            painter = painterResource(item.icon),
            contentDescription = "${item.displayName} icon",
            modifier =
                modifier
                    .size(size)
                    .clip(RoundedCornerShape(size / AppCornerRatio)),
            contentScale = ContentScale.Crop,
        )
    } else {
        // Terminal Window Style Box (Gruvbox Light)
        Column(
            modifier =
                modifier
                    .size(size)
                    .clip(RoundedCornerShape(size / TerminalCornerRatio))
                    .background(TerminalBackgroundColor)
                    .padding(size / TerminalPaddingRatio),
        ) {
            // macOS Window Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(size / WindowButtonSpacingRatio),
                modifier = Modifier.padding(bottom = size / TerminalPaddingRatio),
            ) {
                Box(modifier = Modifier.size(size / WindowButtonSizeRatio).clip(CircleShape).background(WindowCloseButtonColor))
                Box(modifier = Modifier.size(size / WindowButtonSizeRatio).clip(CircleShape).background(WindowMinimizeButtonColor))
                Box(modifier = Modifier.size(size / WindowButtonSizeRatio).clip(CircleShape).background(WindowZoomButtonColor))
            }

            // Terminal Prompt
            Text(
                text = "> ${item.displayName}",
                color = TerminalPromptColor,
                fontSize = (size.value / PromptFontRatio).sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                lineHeight = (size.value / PromptLineHeightRatio).sp,
            )
        }
    }
}
