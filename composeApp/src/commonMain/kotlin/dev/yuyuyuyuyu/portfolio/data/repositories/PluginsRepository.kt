package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.Readme
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.plugin_desc_composepwa
import portfolio.composeapp.generated.resources.plugin_desc_ghadmissionhook
import portfolio.composeapp.generated.resources.plugin_desc_semicolon
import portfolio.composeapp.generated.resources.plugin_mot_composepwa
import portfolio.composeapp.generated.resources.plugin_mot_semicolon

@Inject
class PluginsRepository {
    private val plugins =
        listOf(
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
            ),
            Product(
                nameFallback = "claude-gh-admission-hook",
                descriptionRes = Res.string.plugin_desc_ghadmissionhook,
                techStack = setOf(TechStack.ClaudeCode, TechStack.Kotlin),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/dotfiles/tree/main/claude-gh-admission-hook",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Plugin,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.ClaudeCode),
                readme =
                    Readme(
                        rawUrl =
                            "https://raw.githubusercontent.com/yuyuyuyuyu-dev/dotfiles/main/" +
                                "claude-gh-admission-hook/README.md",
                        pageUrl =
                            "https://github.com/yuyuyuyuyu-dev/dotfiles/blob/main/" +
                                "claude-gh-admission-hook/README.md",
                        japaneseFile = "files/readmes/ja/claude-gh-admission-hook.md",
                    ),
            ),
        ).sortedBy { it.nameFallback }

    fun getPlugins() = plugins
}
