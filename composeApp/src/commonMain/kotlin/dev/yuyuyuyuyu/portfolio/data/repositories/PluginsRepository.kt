package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject

import portfolio.composeapp.generated.resources.*

@Inject
class PluginsRepository {
    private val plugins = listOf(
        Product(
            nameFallback = "insert-end-semicolon.nvim",
            descriptionRes = Res.string.plugin_desc_semicolon,
            techStack = setOf(TechStack.Neovim, TechStack.Lua),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/insert-end-semicolon.nvim",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Plugin,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Neovim),
            motivationRes = Res.string.plugin_mot_semicolon,
        ),
        Product(
            nameFallback = "ComposePWA",
            descriptionRes = Res.string.plugin_desc_composepwa,
            techStack = setOf(TechStack.Kotlin, TechStack.ComposeMultiplatform, TechStack.Gradle),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ComposePWA",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Plugin,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Gradle),
            motivationRes = Res.string.plugin_mot_composepwa,
        )
    ).sortedBy { it.nameFallback }

    fun getPlugins() = plugins
}
