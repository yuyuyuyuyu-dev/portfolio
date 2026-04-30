package dev.yuyuyuyuyu.portfolio.data.models

import org.jetbrains.compose.resources.StringResource

data class Product(
    override val nameRes: StringResource? = null,
    override val nameFallback: String? = null,
    override val descriptionRes: StringResource,
    override val techStack: Set<TechStack>,
    override val repositoryUrl: String,
    override val platforms: Set<Platform>,
    override val category: ProductCategory,
    override val motivationRes: StringResource? = null,
    override val installCommand: String? = null,
) : PortfolioItem {
    init {
        require(nameRes != null || nameFallback != null) { "Either nameRes or nameFallback must be provided" }
    }
}
