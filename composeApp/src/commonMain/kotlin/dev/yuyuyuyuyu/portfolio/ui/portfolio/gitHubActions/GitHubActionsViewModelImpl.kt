package dev.yuyuyuyuyu.portfolio.ui.portfolio.gitHubActions

import androidx.lifecycle.ViewModel
import dev.yuyuyuyuyu.portfolio.data.repositories.GitHubActionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class GitHubActionsViewModelImpl(
    gitHubActionsRepository: GitHubActionsRepository,
) : ViewModel(),
    GitHubActionsViewModel {
    private val _uiState = MutableStateFlow(GitHubActionsUiState(gitHubActions = gitHubActionsRepository.getGitHubActions()))
    override val uiState: StateFlow<GitHubActionsUiState> = _uiState.asStateFlow()
}
