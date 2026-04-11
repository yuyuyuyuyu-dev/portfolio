package dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries

import androidx.lifecycle.ViewModel
import dev.yuyuyuyuyu.portfolio.data.repositories.LibrariesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class LibrariesViewModelImpl(
    librariesRepository: LibrariesRepository,
) : LibrariesViewModel, ViewModel() {
    private val _uiState = MutableStateFlow(LibrariesUiState(libraries = librariesRepository.getLibraries()))
    override val uiState: StateFlow<LibrariesUiState> = _uiState.asStateFlow()
}
