package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class PortfolioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PortfolioUiState(message = "Hello, world!"))
    val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()
}
