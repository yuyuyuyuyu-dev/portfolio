package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.lib_desc_appbar
import portfolio.composeapp.generated.resources.lib_desc_licenses
import portfolio.composeapp.generated.resources.lib_desc_littlediva
import portfolio.composeapp.generated.resources.lib_desc_permissions
import portfolio.composeapp.generated.resources.lib_desc_simpleappbar
import portfolio.composeapp.generated.resources.lib_desc_typography
import portfolio.composeapp.generated.resources.lib_mot_appbar
import portfolio.composeapp.generated.resources.lib_mot_licenses
import portfolio.composeapp.generated.resources.lib_mot_littlediva
import portfolio.composeapp.generated.resources.lib_mot_simpleappbar
import portfolio.composeapp.generated.resources.lib_mot_typography

@Inject
class LibrariesRepository {
    private val libraries =
        listOf(
            Product(
                nameFallback = "ngx-mat-third-party-licenses-list-view",
                descriptionRes = Res.string.lib_desc_licenses,
                techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.TypeScript),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-third-party-licenses-list-view",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.lib_mot_licenses,
            ),
            Product(
                nameFallback = "@yuyuyuyuyu-dev/ngx-mat-my-simple-appbar",
                descriptionRes = Res.string.lib_desc_appbar,
                techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.TypeScript),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-my-simple-appbar",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.lib_mot_appbar,
            ),
            Product(
                nameFallback = "@yuyuyuyuyu-dev/ngx-mat-little-diva-theme",
                descriptionRes = Res.string.lib_desc_littlediva,
                techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.Sass),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-little-diva-theme",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
                motivationRes = Res.string.lib_mot_littlediva,
            ),
            Product(
                nameFallback = "RequestPermissions",
                descriptionRes = Res.string.lib_desc_permissions,
                techStack = setOf(TechStack.Android, TechStack.Kotlin, TechStack.JetpackCompose),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/RequestPermissions",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Android),
            ),
            Product(
                nameFallback = "CreateTypography",
                descriptionRes = Res.string.lib_desc_typography,
                techStack =
                    setOf(
                        TechStack.Android,
                        TechStack.Kotlin,
                        TechStack.JetpackCompose,
                        TechStack.ComposeMultiplatform,
                    ),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/CreateTypography",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
                platforms =
                    setOf(
                        dev.yuyuyuyuyu.portfolio.data.models.Platform.Android,
                        dev.yuyuyuyuyu.portfolio.data.models.Platform.Ios,
                        dev.yuyuyuyuyu.portfolio.data.models.Platform.Web,
                    ),
                motivationRes = Res.string.lib_mot_typography,
            ),
            Product(
                nameFallback = "SimpleTopAppBar",
                descriptionRes = Res.string.lib_desc_simpleappbar,
                techStack =
                    setOf(
                        TechStack.Android,
                        TechStack.Kotlin,
                        TechStack.JetpackCompose,
                        TechStack.ComposeMultiplatform,
                    ),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/SimpleTopAppBar",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
                platforms =
                    setOf(
                        dev.yuyuyuyuyu.portfolio.data.models.Platform.Android,
                        dev.yuyuyuyuyu.portfolio.data.models.Platform.Ios,
                        dev.yuyuyuyuyu.portfolio.data.models.Platform.Web,
                    ),
                motivationRes = Res.string.lib_mot_simpleappbar,
            ),
        ).sortedBy { it.nameFallback }

    fun getLibraries() = libraries
}
