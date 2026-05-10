package dev.yuyuyuyuyu.portfolio.ui.portfolio.templates

import androidx.lifecycle.ViewModel
import dev.yuyuyuyuyu.portfolio.data.repositories.TemplatesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class TemplatesViewModelImpl(
    templatesRepository: TemplatesRepository,
) : ViewModel(),
    TemplatesViewModel {
    private val _uiState = MutableStateFlow(TemplatesUiState(templates = templatesRepository.getTemplates()))
    override val uiState: StateFlow<TemplatesUiState> = _uiState.asStateFlow()
}
