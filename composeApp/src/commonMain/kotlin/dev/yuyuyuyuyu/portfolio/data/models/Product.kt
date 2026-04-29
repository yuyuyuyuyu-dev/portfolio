package dev.yuyuyuyuyu.portfolio.data.models

data class Product(
    override val name: String,
    override val description: String,
    override val techStack: Set<TechStack>,
    override val repositoryUrl: String,
    override val platforms: Set<Platform>,
    override val category: ProductCategory,
    override val motivation: String? = null,
    override val installCommand: String? = null,
) : PortfolioItem
