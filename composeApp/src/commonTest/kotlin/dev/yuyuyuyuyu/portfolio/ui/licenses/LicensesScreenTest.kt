package dev.yuyuyuyuyu.portfolio.ui.licenses

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test

class LicensesScreenTest {
    @OptIn(ExperimentalTestApi::class)
    @Test
    fun licensesScreen_rendersWithoutCrashing() =
        runComposeUiTest {
            setContent {
                LicensesScreen(
                    onNavigateBack = {},
                )
            }

            // Ensure it renders without crashing.
        }
}
