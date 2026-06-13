package dev.yuyuyuyuyu.portfolio.ui.portfolio.today

import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem

/**
 * The hand-picked items highlighted on the Today screen. Every entry is
 * nullable because the catalog data may not contain the item (yet).
 */
data class TodayFeaturedItems(
    val howOldAmI: App? = null,
    val html2pdf: PortfolioItem? = null,
    val notPullingCalc: App? = null,
    val inputSourceHandler: App? = null,
    val composePwa: PortfolioItem? = null,
    val businessCard: PortfolioItem? = null,
)
