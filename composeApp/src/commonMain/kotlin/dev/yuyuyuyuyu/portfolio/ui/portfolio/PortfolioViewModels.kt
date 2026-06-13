package dev.yuyuyuyuyu.portfolio.ui.portfolio

import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.apps.AppsViewModelImpl
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.cliTools.CliToolsViewModelImpl
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.libraries.LibrariesViewModelImpl
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.plugins.PluginsViewModelImpl
import dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModel
import dev.yuyuyuyuyu.portfolio.ui.portfolio.templates.TemplatesViewModelImpl
import me.tatarka.inject.annotations.Inject

/**
 * The view models shared by the portfolio screens, grouped so that they can
 * travel through the navigation graph as a single parameter.
 */
@Inject
class PortfolioViewModels(
    apps: AppsViewModelImpl,
    libraries: LibrariesViewModelImpl,
    plugins: PluginsViewModelImpl,
    cliTools: CliToolsViewModelImpl,
    templates: TemplatesViewModelImpl,
) {
    val apps: AppsViewModel = apps
    val libraries: LibrariesViewModel = libraries
    val plugins: PluginsViewModel = plugins
    val cliTools: CliToolsViewModel = cliTools
    val templates: TemplatesViewModel = templates
}
