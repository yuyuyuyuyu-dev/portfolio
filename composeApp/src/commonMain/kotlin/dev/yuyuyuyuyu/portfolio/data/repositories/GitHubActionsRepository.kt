package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.action_desc_assertnochanges
import portfolio.composeapp.generated.resources.action_mot_assertnochanges

@Inject
class GitHubActionsRepository {
    private val gitHubActions =
        listOf(
            Product(
                nameFallback = "assert-no-unexpected-changes",
                descriptionRes = Res.string.action_desc_assertnochanges,
                techStack = setOf(TechStack.GitHubActions, TechStack.Bash, TechStack.Docker),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/assert-no-unexpected-changes",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.GitHubAction,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.GitHubActions),
                motivationRes = Res.string.action_mot_assertnochanges,
                installCommand =
                    "- uses: yuyuyuyuyu-dev/assert-no-unexpected-changes@v1\n" +
                        "  with:\n" +
                        "    act: {Command to verify}",
            ),
        ).sortedBy { it.nameFallback }

    fun getGitHubActions() = gitHubActions
}
