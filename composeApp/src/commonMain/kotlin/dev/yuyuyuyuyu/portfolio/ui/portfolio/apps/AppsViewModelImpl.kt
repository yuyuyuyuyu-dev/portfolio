package dev.yuyuyuyuyu.portfolio.ui.portfolio.apps

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class AppsViewModelImpl : AppsViewModel, ViewModel() {
    override val uiState: StateFlow<AppsUiState>
        get() = TODO("Not yet implemented")
}
