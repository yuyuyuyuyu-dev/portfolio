package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack

class PluginsRepository {
    private val plugins = listOf(
        Product(
            name = "insert-end-semicolon.nvim",
            description = "行末に\";\"を挿入するNeovimプラグイン",
            techStack = setOf(TechStack.Neovim, TechStack.Lua),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/insert-end-semicolon.nvim",
        ),
        Product(
            name = "ComposePWA",
            description = "Compose Multiplatform製WebアプリをPWA化するGradleプラグイン",
            techStack = setOf(TechStack.Kotlin, TechStack.ComposeMultiplatform, TechStack.Gradle),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ComposePWA",
        )
    ).sortedBy { it.repositoryUrl }

    fun getPlugins() = plugins
}
