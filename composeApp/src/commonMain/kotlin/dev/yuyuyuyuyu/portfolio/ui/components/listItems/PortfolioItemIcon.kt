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
private val TerminalBackgroundColor = Color(color = 0xFFFBF1C7)
private val TerminalPromptColor = Color(color = 0xFF3C3836)
private val WindowCloseButtonColor = Color(color = 0xFFCC241D)
private val WindowMinimizeButtonColor = Color(color = 0xFFD79921)
private val WindowZoomButtonColor = Color(color = 0xFF98971A)

// Dimensions expressed as fractions of the icon size
private const val APP_CORNER_RATIO = 6f
private const val TERMINAL_CORNER_RATIO = 8f
private const val TERMINAL_PADDING_RATIO = 10f
private const val WINDOW_BUTTON_SPACING_RATIO = 20f
private const val WINDOW_BUTTON_SIZE_RATIO = 12f
private const val PROMPT_FONT_RATIO = 8f
private const val PROMPT_LINE_HEIGHT_RATIO = 6f

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
                    .clip(RoundedCornerShape(size / APP_CORNER_RATIO)),
            contentScale = ContentScale.Crop,
        )
    } else {
        // Terminal Window Style Box (Gruvbox Light)
        Column(
            modifier =
                modifier
                    .size(size)
                    .clip(RoundedCornerShape(size / TERMINAL_CORNER_RATIO))
                    .background(TerminalBackgroundColor)
                    .padding(size / TERMINAL_PADDING_RATIO),
        ) {
            // macOS Window Buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(size / WINDOW_BUTTON_SPACING_RATIO),
                modifier = Modifier.padding(bottom = size / TERMINAL_PADDING_RATIO),
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(size / WINDOW_BUTTON_SIZE_RATIO)
                            .clip(CircleShape)
                            .background(WindowCloseButtonColor),
                )
                Box(
                    modifier =
                        Modifier
                            .size(size / WINDOW_BUTTON_SIZE_RATIO)
                            .clip(CircleShape)
                            .background(WindowMinimizeButtonColor),
                )
                Box(
                    modifier =
                        Modifier
                            .size(size / WINDOW_BUTTON_SIZE_RATIO)
                            .clip(CircleShape)
                            .background(WindowZoomButtonColor),
                )
            }

            // Terminal Prompt
            Text(
                text = "> ${item.displayName}",
                color = TerminalPromptColor,
                fontSize = (size.value / PROMPT_FONT_RATIO).sp,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                lineHeight = (size.value / PROMPT_LINE_HEIGHT_RATIO).sp,
            )
        }
    }
}
