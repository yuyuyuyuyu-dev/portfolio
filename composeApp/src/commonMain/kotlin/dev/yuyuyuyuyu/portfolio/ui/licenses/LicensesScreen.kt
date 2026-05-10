package dev.yuyuyuyuyu.portfolio.ui.licenses

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import dev.yuyuyuyuyu.simpleTopAppBar.SimpleTopAppBar
import org.jetbrains.compose.resources.ExperimentalResourceApi
import portfolio.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LicensesScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var aboutLibsJson by remember { mutableStateOf("") }
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(Unit) {
        aboutLibsJson = Res.readBytes("files/aboutlibraries.json").decodeToString()
    }

    val libs by produceLibraries(aboutLibsJson)

    Scaffold(
        topBar = {
            SimpleTopAppBar(
                title = "Licenses",
                navigateBackIsPossible = true,
                onNavigateBackButtonClick = onNavigateBack,
                onOpenSourceLicensesButtonClick = {},
                onSourceCodeButtonClick = {
                    uriHandler.openUri("https://github.com/yuyuyuyuyu-dev/portfolio")
                },
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        if (aboutLibsJson.isNotEmpty() && libs != null) {
            LibrariesContainer(
                libraries = libs!!,
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
