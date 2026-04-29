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
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Plugin,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Neovim),
            motivation = "Rustなど行末にセミコロンが必要な言語で、わざわざ行末にカーソルを移動させてからセミコロンを入力するのが面倒だったため。現在位置を維持したまま行末にセミコロンを挿入する関数を提供するプラグインを作成した。",
        ),
        Product(
            name = "ComposePWA",
            description = "Compose Multiplatform製WebアプリをPWA化するGradleプラグイン",
            techStack = setOf(TechStack.Kotlin, TechStack.ComposeMultiplatform, TechStack.Gradle),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ComposePWA",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Plugin,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Gradle),
            motivation = "Compose MultiplatformでPWAを作るにあたって、新規プロジェクトを作成するたびにPWA化に必要なファイルや記述をコピペしてくるのがめんどくさすぎたため。",
        )
    ).sortedBy { it.name }

    fun getPlugins() = plugins
}
