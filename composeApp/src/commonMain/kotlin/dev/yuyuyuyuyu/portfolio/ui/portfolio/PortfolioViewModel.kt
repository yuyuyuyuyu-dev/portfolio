package dev.yuyuyuyuyu.portfolio.ui.portfolio

import kotlinx.coroutines.flow.StateFlow

interface PortfolioViewModel {
    val uiState: StateFlow<PortfolioUiState>
}
