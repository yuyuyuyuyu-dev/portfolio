package dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries

import kotlinx.coroutines.flow.StateFlow

interface LibrariesViewModel {
    val uiState: StateFlow<LibrariesUiState>
}
