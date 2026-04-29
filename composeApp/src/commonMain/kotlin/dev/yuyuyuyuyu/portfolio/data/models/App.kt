package dev.yuyuyuyuyu.portfolio.data.models

import org.jetbrains.compose.resources.DrawableResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.default_icon

data class App(
    override val name: String,
    override val description: String,
    override val techStack: Set<TechStack>,
    override val repositoryUrl: String,
    override val platforms: Set<Platform>,
    override val category: ProductCategory = ProductCategory.App,
    override val motivation: String? = null,
    override val installCommand: String? = null,
    val screenshots: List<DrawableResource> = listOf(),
    val url: String? = null,
    val icon: DrawableResource = Res.drawable.default_icon,
) : PortfolioItem
