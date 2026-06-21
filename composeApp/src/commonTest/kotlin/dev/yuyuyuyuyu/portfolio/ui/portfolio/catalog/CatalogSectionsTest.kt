package dev.yuyuyuyuyu.portfolio.ui.portfolio.catalog

import dev.yuyuyuyuyu.portfolio.data.models.App
import dev.yuyuyuyuyu.portfolio.data.models.Platform
import dev.yuyuyuyuyu.portfolio.data.models.PortfolioItem
import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.ProductCategory
import org.jetbrains.compose.resources.StringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.app_name
import portfolio.composeapp.generated.resources.ui_cli_tools
import portfolio.composeapp.generated.resources.ui_libraries
import portfolio.composeapp.generated.resources.ui_mac_apps
import portfolio.composeapp.generated.resources.ui_mobile_other_apps
import portfolio.composeapp.generated.resources.ui_plugins
import portfolio.composeapp.generated.resources.ui_templates
import portfolio.composeapp.generated.resources.ui_web_apps
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CatalogSectionsTest {
    @Test
    fun app_isBucketedByItsPlatform() {
        val sections =
            catalogSections(
                allApps = listOf(app("Web App", Platform.Web), app("Mac App", Platform.MacOS)),
                allProducts = emptyList(),
            )

        assertEquals(listOf("Web App"), sections.section(Res.string.ui_web_apps))
        assertEquals(listOf("Mac App"), sections.section(Res.string.ui_mac_apps))
    }

    @Test
    fun multiPlatformApp_appearsInEveryMatchingBucket() {
        val sections =
            catalogSections(
                allApps = listOf(app("Cross", Platform.MacOS, Platform.Web)),
                allProducts = emptyList(),
            )

        assertEquals(listOf("Cross"), sections.section(Res.string.ui_web_apps))
        assertEquals(listOf("Cross"), sections.section(Res.string.ui_mac_apps))
    }

    @Test
    fun androidOrIosApp_goesIntoMobileAndOther() {
        val sections =
            catalogSections(
                allApps = listOf(app("Mobile", Platform.Android)),
                allProducts = emptyList(),
            )

        assertEquals(listOf("Mobile"), sections.section(Res.string.ui_mobile_other_apps))
    }

    @Test
    fun appMatchingNoKnownBucket_fallsIntoMobileAndOther() {
        val sections =
            catalogSections(
                allApps = listOf(app("Win", Platform.Windows)),
                allProducts = emptyList(),
            )

        assertEquals(listOf("Win"), sections.section(Res.string.ui_mobile_other_apps))
    }

    @Test
    fun bucketedApp_isNotAlsoDumpedIntoMobileAndOther() {
        val sections =
            catalogSections(
                allApps = listOf(app("Web", Platform.Web)),
                allProducts = emptyList(),
            )

        assertTrue(sections.section(Res.string.ui_mobile_other_apps).isEmpty())
    }

    @Test
    fun products_areSplitByCategoryAndSortedByName() {
        val sections =
            catalogSections(
                allApps = emptyList(),
                allProducts =
                    listOf(
                        product("zeta-lib", ProductCategory.Library),
                        product("alpha-lib", ProductCategory.Library),
                        product("my-plugin", ProductCategory.Plugin),
                    ),
            )

        assertEquals(listOf("alpha-lib", "zeta-lib"), sections.section(Res.string.ui_libraries))
        assertEquals(listOf("my-plugin"), sections.section(Res.string.ui_plugins))
    }

    @Test
    fun emptySections_areDropped() {
        val sections =
            catalogSections(
                allApps = listOf(app("Web", Platform.Web)),
                allProducts = emptyList(),
            )

        assertEquals(listOf(Res.string.ui_web_apps), sections.map { it.first })
    }

    @Test
    fun sections_keepTheirDefinedOrder() {
        val sections =
            catalogSections(
                allApps =
                    listOf(
                        app("WebOnly", Platform.Web),
                        app("MacOnly", Platform.MacOS),
                        app("AndroidOnly", Platform.Android),
                    ),
                allProducts =
                    listOf(
                        product("plug", ProductCategory.Plugin),
                        product("lib", ProductCategory.Library),
                        product("cli", ProductCategory.CliTool),
                        product("tmpl", ProductCategory.Template),
                    ),
            )

        assertEquals(
            listOf(
                Res.string.ui_web_apps,
                Res.string.ui_plugins,
                Res.string.ui_libraries,
                Res.string.ui_cli_tools,
                Res.string.ui_templates,
                Res.string.ui_mac_apps,
                Res.string.ui_mobile_other_apps,
            ),
            sections.map { it.first },
        )
    }

    private fun List<Pair<StringResource, List<PortfolioItem>>>.section(titleRes: StringResource): List<String?> =
        firstOrNull { it.first == titleRes }?.second?.map { it.nameFallback } ?: emptyList()

    private fun app(
        name: String,
        vararg platforms: Platform,
    ): App =
        App(
            nameFallback = name,
            descriptionRes = Res.string.app_name,
            techStack = emptySet(),
            repositoryUrl = "https://github.com",
            platforms = platforms.toSet(),
            category = ProductCategory.App,
        )

    private fun product(
        name: String,
        category: ProductCategory,
    ): Product =
        Product(
            nameFallback = name,
            descriptionRes = Res.string.app_name,
            techStack = emptySet(),
            repositoryUrl = "https://github.com",
            platforms = emptySet(),
            category = category,
        )
}
