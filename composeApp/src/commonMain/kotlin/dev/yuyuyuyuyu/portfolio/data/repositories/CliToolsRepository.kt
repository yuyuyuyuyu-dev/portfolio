package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.tool_desc_gen_licenses
import portfolio.composeapp.generated.resources.tool_desc_household
import portfolio.composeapp.generated.resources.tool_desc_html2pdf
import portfolio.composeapp.generated.resources.tool_desc_jarc
import portfolio.composeapp.generated.resources.tool_desc_myde
import portfolio.composeapp.generated.resources.tool_desc_myde_command
import portfolio.composeapp.generated.resources.tool_desc_myinstaller
import portfolio.composeapp.generated.resources.tool_desc_newproject
import portfolio.composeapp.generated.resources.tool_desc_rubberduck
import portfolio.composeapp.generated.resources.tool_mot_gen_licenses
import portfolio.composeapp.generated.resources.tool_mot_household
import portfolio.composeapp.generated.resources.tool_mot_html2pdf
import portfolio.composeapp.generated.resources.tool_mot_jarc
import portfolio.composeapp.generated.resources.tool_mot_myde
import portfolio.composeapp.generated.resources.tool_mot_myde_command
import portfolio.composeapp.generated.resources.tool_mot_myinstaller
import portfolio.composeapp.generated.resources.tool_mot_newproject

@Inject
class CliToolsRepository {
    private val cliTools =
        listOf(
            Product(
                nameFallback = "household",
                descriptionRes = Res.string.tool_desc_household,
                techStack = setOf(TechStack.Python),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/household",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
                motivationRes = Res.string.tool_mot_household,
            ),
            Product(
                nameFallback = "myDE",
                descriptionRes = Res.string.tool_desc_myde,
                techStack = setOf(TechStack.Docker),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/myDE",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Linux, dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
                motivationRes = Res.string.tool_mot_myde,
            ),
            Product(
                nameFallback = "myde_command",
                descriptionRes = Res.string.tool_desc_myde_command,
                techStack = setOf(TechStack.Bash),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/myde_command",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
                motivationRes = Res.string.tool_mot_myde_command,
            ),
            Product(
                nameFallback = "newProject",
                descriptionRes = Res.string.tool_desc_newproject,
                techStack = setOf(TechStack.Bash),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/newProject",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
                motivationRes = Res.string.tool_mot_newproject,
            ),
            Product(
                nameFallback = "myInstaller",
                descriptionRes = Res.string.tool_desc_myinstaller,
                techStack = setOf(TechStack.Bash),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/myInstaller",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
                motivationRes = Res.string.tool_mot_myinstaller,
            ),
            Product(
                nameFallback = "jarc",
                descriptionRes = Res.string.tool_desc_jarc,
                techStack = setOf(TechStack.Bash),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/jarc",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
                motivationRes = Res.string.tool_mot_jarc,
            ),
            Product(
                nameFallback = "rubber-duck",
                descriptionRes = Res.string.tool_desc_rubberduck,
                techStack = setOf(TechStack.Go),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/rubber-duck",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
            ),
            Product(
                nameFallback = "generate-third-party-licenses-json",
                descriptionRes = Res.string.tool_desc_gen_licenses,
                techStack = setOf(TechStack.TypeScript, TechStack.NodeJs),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/generate-third-party-licenses-json",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
                motivationRes = Res.string.tool_mot_gen_licenses,
            ),
            Product(
                nameFallback = "@yuyuyuyuyu-dev/html2pdf",
                descriptionRes = Res.string.tool_desc_html2pdf,
                techStack = setOf(TechStack.TypeScript, TechStack.NodeJs),
                repositoryUrl = "https://github.com/yuyuyuyuyu-dev/html2pdf",
                category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.CliTool,
                platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
                motivationRes = Res.string.tool_mot_html2pdf,
                installCommand =
                    "npx @yuyuyuyuyu-dev/html2pdf " +
                        "--chromium-path \"\$(type -p chromium-browser)\" " +
                        "--src {Source HTML file path} " +
                        "--dest {Destination PDF path}",
            ),
        ).sortedBy { it.nameFallback }

    fun getCliTools() = cliTools
}
