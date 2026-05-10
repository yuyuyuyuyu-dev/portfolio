package dev.yuyuyuyuyu.portfolio.ui.portfolio.apps

import androidx.lifecycle.ViewModel
import dev.yuyuyuyuyu.portfolio.data.repositories.AppsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class AppsViewModelImpl(
    appsRepository: AppsRepository,
) : ViewModel(),
    AppsViewModel {
    private val _uiState = MutableStateFlow(AppsUiState(apps = appsRepository.getApps()))
    override val uiState: StateFlow<AppsUiState> = _uiState.asStateFlow()
}
