package dev.yuyuyuyuyu.portfolio.data.models

import org.jetbrains.compose.resources.StringResource

interface PortfolioItem {
    val nameRes: StringResource? // Nullable for tools that don't need translation (e.g. ComposePWA)
    val nameFallback: String?    // Used if nameRes is null
    val descriptionRes: StringResource
    val techStack: Set<TechStack>
    val repositoryUrl: String
    val platforms: Set<Platform>
    val category: ProductCategory
    val motivationRes: StringResource?
    val installCommand: String?
}
