package dev.yuyuyuyuyu.portfolio.di

import dev.yuyuyuyuyu.portfolio.ui.portfolio.PortfolioScreen
import me.tatarka.inject.annotations.Component

@Component
abstract class AppComponent {
    abstract val portfolioScreen: PortfolioScreen
}
