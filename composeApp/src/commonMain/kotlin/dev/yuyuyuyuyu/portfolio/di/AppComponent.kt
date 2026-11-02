package dev.yuyuyuyuyu.portfolio.di

import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioScreen
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate

@Component
abstract class AppComponent {
    abstract val portfolioScreen: PortfolioScreen
}

@KmpComponentCreate
expect fun createAppComponent(): AppComponent
