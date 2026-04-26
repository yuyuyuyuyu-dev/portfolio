package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject

@Inject
class CliToolsRepository {
    private val cliTools = listOf(
        Product(
            name = "household",
            description = "おこづかいと手持ちの現金を管理するためのコマンドラインツール",
            techStack = setOf(TechStack.Python),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/household",
        ),
        Product(
            name = "myDE",
            description = "プラグインがインストール済みのneovimが起動するDockerコンテナ",
            techStack = setOf(TechStack.Docker),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/myDE",
        ),
        Product(
            name = "myde_command",
            description = "myDEを簡単に起動するためのシェルスクリプト",
            techStack = setOf(TechStack.Bash),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/myde_command",
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
            motivation = "上記のDockerコンテナ「myDE」を起動するコマンドすら毎回打つのが面倒だったため、簡単に起動するためのスクリプトを作った。",
        ),
        Product(
            name = "newProject",
            description = "指定された名前でディレクトリとファイルを一括で作成するシェルスクリプト",
            techStack = setOf(TechStack.Bash),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/newProject",
        ),
        Product(
            name = "myInstaller",
            description = "引数に指定されたファイルを ~/myCommands/ にコピーしてから実行権限を付与するシェルスクリプト",
            techStack = setOf(TechStack.Bash),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/myInstaller",
        ),
        Product(
            name = "jarc",
            description = "jarファイルの生成に必要な作業をひとまとめにしたシェルスクリプト",
            techStack = setOf(TechStack.Bash),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/jarc",
        ),
        Product(
            name = "rubber-duck",
            description = "Go言語製のラバーダック",
            techStack = setOf(TechStack.Go),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/rubber-duck",
        ),
        Product(
            name = "generate-third-party-licenses-json",
            description = "Node.jsプロジェクトのサードパーティライブラリのライセンスを収集し、JSON形式でエクスポートするCLIツール",
            techStack = setOf(TechStack.TypeScript, TechStack.NodeJs),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/generate-third-party-licenses-json",
        ),
        Product(
            name = "@yuyuyuyuyu-dev/html2pdf",
            description = "Converts HTML to PDF using Chromium.",
            techStack = setOf(TechStack.TypeScript, TechStack.NodeJs),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/html2pdf",
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Cli),
            motivation = "AndroidスマホでGeminiを使ってHTMLとCSSの名刺を作っていた時、HTMLをPDFに変換するためだけにわざわざPCを取り出すのが面倒だったため。スマホのTermux環境だけで完結させるために作った。",
            installCommand = "npx @yuyuyuyuyu-dev/html2pdf --chromium-path \"\$(type -p chromium-browser)\" --src {Source HTML file path} --dest {Destination PDF path}",
        ),
    ).sortedBy { it.repositoryUrl }

    fun getCliTools() = cliTools
}
