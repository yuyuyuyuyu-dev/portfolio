package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject

import portfolio.composeapp.generated.resources.*

@Inject
class TemplatesRepository {
    private val templates = listOf(
        Product(
            nameFallback = "business-card-template",
            descriptionRes = Res.string.tpl_desc_businesscard,
            techStack = setOf(TechStack.Html, TechStack.Css),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/business-card-template",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Template,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            motivationRes = Res.string.tpl_mot_businesscard,
        )
    ).sortedBy { it.nameFallback }

    fun getTemplates() = templates
}