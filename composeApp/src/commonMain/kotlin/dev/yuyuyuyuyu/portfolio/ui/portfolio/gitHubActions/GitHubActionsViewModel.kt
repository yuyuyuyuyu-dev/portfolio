package dev.yuyuyuyuyu.portfolio.ui.portfolio.gitHubActions

import kotlinx.coroutines.flow.StateFlow

interface GitHubActionsViewModel {
    val uiState: StateFlow<GitHubActionsUiState>
}
