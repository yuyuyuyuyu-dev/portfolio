package dev.yuyuyuyuyu.portfolio.ui.portfolio.catalog

import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import org.jetbrains.compose.resources.StringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ui_cli_tools
import portfolio.composeapp.generated.resources.ui_libraries
import portfolio.composeapp.generated.resources.ui_mac_apps
import portfolio.composeapp.generated.resources.ui_mobile_other_apps
import portfolio.composeapp.generated.resources.ui_plugins
import portfolio.composeapp.generated.resources.ui_templates
import portfolio.composeapp.generated.resources.ui_web_apps

/**
 * Groups the catalog data into the ordered, titled sections shown on the
 * catalog screen.
 *
 * Apps in [allApps] are bucketed by platform; an app supporting several
 * platforms appears in every matching bucket, and any app matching none of the
 * mac / web / Android-iOS buckets falls into "mobile & other". Products in
 * [allProducts] are split by category and sorted by name. Empty sections are
 * dropped. Kept pure so it can be unit-tested without the Compose runtime.
 */
fun catalogSections(
    allApps: List<PortfolioItem>,
    allProducts: List<PortfolioItem>,
): List<Pair<StringResource, List<PortfolioItem>>> {
    val macApps = allApps.filter { Platform.MacOS in it.platforms }
    val webApps = allApps.filter { Platform.Web in it.platforms }
    val androidIosApps = allApps.filter { Platform.Android in it.platforms || Platform.Ios in it.platforms }
    val otherApps = allApps - macApps.toSet() - webApps.toSet() - androidIosApps.toSet()

    val cliTools = allProducts.filter { it.category == ProductCategory.CliTool }.sortedBy { it.nameFallback }
    val plugins = allProducts.filter { it.category == ProductCategory.Plugin }.sortedBy { it.nameFallback }
    val libraries = allProducts.filter { it.category == ProductCategory.Library }.sortedBy { it.nameFallback }
    val templates = allProducts.filter { it.category == ProductCategory.Template }.sortedBy { it.nameFallback }

    return listOf(
        Res.string.ui_web_apps to webApps,
        Res.string.ui_plugins to plugins,
        Res.string.ui_libraries to libraries,
        Res.string.ui_cli_tools to cliTools,
        Res.string.ui_templates to templates,
        Res.string.ui_mac_apps to macApps,
        Res.string.ui_mobile_other_apps to (androidIosApps + otherApps),
    ).filter { (_, items) -> items.isNotEmpty() }
}
