package dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins

import kotlinx.coroutines.flow.StateFlow

interface PluginsViewModel {
    val uiState: StateFlow<PluginsUiState>
}
