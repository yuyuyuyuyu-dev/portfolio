package dev.yuyuyuyuyu.portfolio.utils

import androidx.compose.ui.platform.Clipboard

expect suspend fun Clipboard.setPlainText(text: String)
