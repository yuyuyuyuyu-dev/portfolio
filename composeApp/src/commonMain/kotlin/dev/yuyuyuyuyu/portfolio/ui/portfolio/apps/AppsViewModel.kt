package dev.yuyuyuyuyu.portfolio.ui.portfolio.apps

import kotlinx.coroutines.flow.StateFlow

interface AppsViewModel {
    val uiState: StateFlow<AppsUiState>
}