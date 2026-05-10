package dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools

import androidx.lifecycle.ViewModel
import dev.yuyuyuyuyu.portfolio.data.repositories.CliToolsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class CliToolsViewModelImpl(
    cliToolsRepository: CliToolsRepository,
) : ViewModel(),
    CliToolsViewModel {
    private val _uiState = MutableStateFlow(CliToolsUiState(cliTools = cliToolsRepository.getCliTools()))
    override val uiState: StateFlow<CliToolsUiState> = _uiState.asStateFlow()
}
