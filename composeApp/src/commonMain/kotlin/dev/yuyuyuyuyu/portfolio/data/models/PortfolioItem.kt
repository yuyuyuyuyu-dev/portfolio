package dev.yuyuyuyuyu.portfolio.data.models

interface PortfolioItem {
    val name: String
    val description: String
    val techStack: Set<TechStack>
    val repositoryUrl: String
    val platforms: Set<Platform>
    val category: ProductCategory
    val motivation: String?
    val installCommand: String?
}
