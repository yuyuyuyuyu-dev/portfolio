package dev.yuyuyuyuyu.portfolio.ui.licenses

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import org.jetbrains.compose.resources.ExperimentalResourceApi
import portfolio.composeapp.generated.resources.Res

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LicensesScreen(modifier: Modifier = Modifier) {
    var aboutLibsJson by remember { mutableStateOf("") }
    LaunchedEffect(Unit) {
        aboutLibsJson = Res.readBytes("files/aboutlibraries.json").decodeToString()
    }

    if (aboutLibsJson.isNotEmpty()) {
        LibrariesContainer(aboutLibsJson = aboutLibsJson, modifier = modifier)
    }
}
