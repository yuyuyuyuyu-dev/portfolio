package dev.yuyuyuyuyu.portfolio.data.models

import org.jetbrains.compose.resources.StringResource
import portfolio.composeapp.generated.resources.*

enum class ProductCategory(val labelRes: StringResource) {
    App(Res.string.ui_app),
    CliTool(Res.string.ui_cli_tools),
    Plugin(Res.string.ui_plugins),
    Library(Res.string.ui_libraries),
    Template(Res.string.ui_templates),
}
