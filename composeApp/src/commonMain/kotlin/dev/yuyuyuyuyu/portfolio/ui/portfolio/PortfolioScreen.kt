package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

typealias PortfolioScreen = @Composable (onNavigateToLicenses: () -> Unit) -> Unit

@Inject
@Composable
fun PortfolioScreen(
    portfolioViewModel: () -> PortfolioViewModel,
    @Assisted onNavigateToLicenses: () -> Unit,
) {
    val viewModel = viewModel { portfolioViewModel() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Text("Portfolio")
        Text(uiState.message)
        Button(
            onClick = onNavigateToLicenses,
            content = { Text("navigate to LicensesScreen") }
        )
    }
}

@Preview
@Composable
private fun PortfolioScreenPreview() {
    PortfolioScreen(
        portfolioViewModel = { PortfolioViewModel() },
        onNavigateToLicenses = {},
    )
}
