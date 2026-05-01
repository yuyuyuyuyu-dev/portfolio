package dev.yuyuyuyuyu.portfolio

import androidx.compose.material3.Text
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.v2.runComposeUiTest
import kotlin.test.Test

class ComposeAppCommonTest {

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testHelloWorldIsDisplayed() = runComposeUiTest {
        setContent {
            Text("Hello, World!")
        }

        onNodeWithText("Hello, World!").assertIsDisplayed()
    }
}
