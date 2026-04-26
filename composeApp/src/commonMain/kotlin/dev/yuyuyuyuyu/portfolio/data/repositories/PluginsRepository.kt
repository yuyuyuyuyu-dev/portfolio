package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject

@Inject
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
            motivation = "Compose MultiplatformでPWAを作るにあたって、新規プロジェクトを作成するたびにPWA化に必要なファイルや記述をコピペしてくるのがめんどくさすぎたため。",
        )
    ).sortedBy { it.repositoryUrl }

    fun getPlugins() = plugins
}
