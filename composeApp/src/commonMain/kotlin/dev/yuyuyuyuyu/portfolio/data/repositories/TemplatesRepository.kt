package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject

@Inject
class TemplatesRepository {
    private val templates = listOf(
        Product(
            name = "business-card-template",
            description = "HTMLとCSSで名刺を作るためのテンプレート",
            techStack = setOf(TechStack.Html, TechStack.Css),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/business-card-template",
            motivation = "名刺を作るためだけに、普段使わないデザインツールの使い方を覚えるのがコスパが悪く面倒だったため。使い慣れたHTMLとCSS（CSSならミリ単位のサイズ指定も可能）で作ってみたら良好だったため、次に作る時のためにテンプレート化した。",
        )
    ).sortedBy { it.repositoryUrl }

    fun getTemplates() = templates
}