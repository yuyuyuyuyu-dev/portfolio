package dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins

import androidx.lifecycle.ViewModel
import dev.yuyuyuyuyu.portfolio.data.repositories.PluginsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class PluginsViewModelImpl(
    pluginsRepository: PluginsRepository,
) : ViewModel(),
    PluginsViewModel {
    private val _uiState = MutableStateFlow(PluginsUiState(plugins = pluginsRepository.getPlugins()))
    override val uiState: StateFlow<PluginsUiState> = _uiState.asStateFlow()
}
