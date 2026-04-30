package dev.yuyuyuyuyu.portfolio.data.models

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.default_icon

data class App(
    override val nameRes: StringResource? = null,
    override val nameFallback: String? = null,
    override val descriptionRes: StringResource,
    override val techStack: Set<TechStack>,
    override val repositoryUrl: String,
    override val platforms: Set<Platform>,
    override val category: ProductCategory = ProductCategory.App,
    override val motivationRes: StringResource? = null,
    override val installCommand: String? = null,
    val screenshots: List<DrawableResource> = listOf(),
    val url: String? = null,
    val icon: DrawableResource = Res.drawable.default_icon,
) : PortfolioItem {
    init {
        require(nameRes != null || nameFallback != null) { "Either nameRes or nameFallback must be provided" }
    }
}
