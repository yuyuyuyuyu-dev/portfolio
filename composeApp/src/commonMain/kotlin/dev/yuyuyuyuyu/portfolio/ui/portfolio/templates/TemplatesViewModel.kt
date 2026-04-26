package dev.yuyuyuyuyu.portfolio.ui.portfolio.templates

import kotlinx.coroutines.flow.StateFlow

interface TemplatesViewModel {
    val uiState: StateFlow<TemplatesUiState>
}
