package dev.yuyuyuyuyu.portfolio.di

import com.slack.circuit.foundation.Circuit
import dev.yuyuyuyuyu.portfolio.data.repositories.AppsRepository
import dev.yuyuyuyuyu.portfolio.data.repositories.CliToolsRepository
import dev.yuyuyuyuyu.portfolio.data.repositories.LibrariesRepository
import dev.yuyuyuyuyu.portfolio.data.repositories.PluginsRepository
import dev.yuyuyuyuyu.portfolio.ui.appList.AppList
import dev.yuyuyuyuyu.portfolio.ui.appList.AppListPresenter
import dev.yuyuyuyuyu.portfolio.ui.appList.AppListScreen
import dev.yuyuyuyuyu.portfolio.ui.cliToolList.CliToolList
import dev.yuyuyuyuyu.portfolio.ui.cliToolList.CliToolListPresenter
import dev.yuyuyuyuyu.portfolio.ui.cliToolList.CliToolListScreen
import dev.yuyuyuyuyu.portfolio.ui.libraryList.LibraryList
import dev.yuyuyuyuyu.portfolio.ui.libraryList.LibraryListPresenter
import dev.yuyuyuyuyu.portfolio.ui.libraryList.LibraryListScreen
import dev.yuyuyuyuyu.portfolio.ui.openSourceLicenseList.OpenSourceLicenseList
import dev.yuyuyuyuyu.portfolio.ui.openSourceLicenseList.OpenSourceLicenseListPresenter
import dev.yuyuyuyuyu.portfolio.ui.openSourceLicenseList.OpenSourceLicenseListScreen
import dev.yuyuyuyuyu.portfolio.ui.pluginList.PluginList
import dev.yuyuyuyuyu.portfolio.ui.pluginList.PluginListPresenter
import dev.yuyuyuyuyu.portfolio.ui.pluginList.PluginListScreen
import dev.yuyuyuyuyu.portfolio.ui.portfolio.Portfolio
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioPresenter
import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioScreen
import org.koin.dsl.module

val portfolioAppModule = module {
    single {
        Circuit.Builder()
            .addUi<PortfolioScreen, PortfolioScreen.State> { _, modifier -> Portfolio(modifier) }
            .addPresenter<PortfolioScreen, PortfolioScreen.State>(PortfolioPresenter())

            .addUi<AppListScreen, AppListScreen.State> { state, modifier -> AppList(state, modifier) }
            .addPresenter<AppListScreen, AppListScreen.State>(AppListPresenter(get()))

            .addUi<LibraryListScreen, LibraryListScreen.State> { state, modifier -> LibraryList(state, modifier) }
            .addPresenter<LibraryListScreen, LibraryListScreen.State>(LibraryListPresenter(get()))

            .addUi<PluginListScreen, PluginListScreen.State> { state, modifier -> PluginList(state, modifier) }
            .addPresenter<PluginListScreen, PluginListScreen.State>(PluginListPresenter(get()))

            .addUi<CliToolListScreen, CliToolListScreen.State> { state, modifier -> CliToolList(state, modifier) }
            .addPresenter<CliToolListScreen, CliToolListScreen.State>(CliToolListPresenter(get()))

            .addUi<OpenSourceLicenseListScreen, OpenSourceLicenseListScreen.State> { _, modifier ->
                OpenSourceLicenseList(modifier)
            }
            .addPresenter<OpenSourceLicenseListScreen, OpenSourceLicenseListScreen.State>(OpenSourceLicenseListPresenter())

            .build()
    }

    single { AppsRepository() }
    single { LibrariesRepository() }
    single { PluginsRepository() }
    single { CliToolsRepository() }
}
