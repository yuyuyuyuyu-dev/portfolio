package dev.yuyuyuyuyu.portfolio.data.models

import org.jetbrains.compose.resources.DrawableResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.default_icon

data class App(
    val name: String,
    val description: String,
    val techStack: Set<TechStack>,
    val repositoryUrl: String,
    val screenshots: List<DrawableResource> = listOf(),
    val url: String? = null,
    val icon: DrawableResource = Res.drawable.default_icon,
)
