package dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools

import kotlinx.coroutines.flow.StateFlow

interface CliToolsViewModel {
    val uiState: StateFlow<CliToolsUiState>
}
