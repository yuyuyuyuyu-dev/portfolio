package dev.yuyuyuyuyu.portfolio.ui.portfolio

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PortfolioScreen(
    onNavigateToLicenses: () -> Unit,
) {
    Text("Portfolio")
    Button(
        onClick = onNavigateToLicenses,
        content = { Text("navigate to LicensesScreen") }
    )
}

@Preview
@Composable
private fun PortfolioScreenPreview() {
    PortfolioScreen(onNavigateToLicenses = {})
}
