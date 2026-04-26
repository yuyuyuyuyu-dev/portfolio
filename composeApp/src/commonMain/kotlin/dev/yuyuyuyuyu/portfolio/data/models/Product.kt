package dev.yuyuyuyuyu.portfolio.data.models

data class Product(
    val name: String,
    val description: String,
    val techStack: Set<TechStack>,
    val repositoryUrl: String,
    val platforms: Set<Platform> = emptySet(),
    val motivation: String? = null,
    val installCommand: String? = null,
)
