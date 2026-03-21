package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack

class LibrariesRepository {
    private val libraries = listOf(
        Product(
            name = "ngx-mat-third-party-licenses-list-view",
            description = "サードパーティライセンスのリストを提供するAngular Material用のコンポーネントライブラリ",
            techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.TypeScript),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-third-party-licenses-list-view",
        ),
        Product(
            name = "@yuyuyuyuyu-dev/ngx-mat-my-simple-appbar",
            description = "Angular Material用のシンプルなアプリバー",
            techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.TypeScript),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-my-simple-appbar",
        ),
        Product(
            name = "@yuyuyuyuyu-dev/ngx-mat-little-diva-theme",
            description = "This library is theme of little diva.",
            techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.Sass),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-little-diva-theme",
        ),
        Product(
            name = "RequestPermissions",
            description = "権限リクエストと処理の同時実行を実現するAndroid用ライブラリ",
            techStack = setOf(TechStack.Android, TechStack.Kotlin, TechStack.JetpackCompose),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/RequestPermissions",
        ),
        Product(
            name = "CreateTypography",
            description = "フォント名やFontFamilyからタイポグラフィを作成する関数を提供するAndroidとCompose Multiplatform用のライブラリ",
            techStack = setOf(
                TechStack.Android,
                TechStack.Kotlin,
                TechStack.JetpackCompose,
                TechStack.ComposeMultiplatform,
            ),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/CreateTypography",
        ),
        Product(
            name = "SimpleTopAppBar",
            description = "戻るボタンとケバブメニューボタンが含まれているAndroidとCompose Multiplatrorm用のシンプルなTopAppBar",
            techStack = setOf(
                TechStack.Android,
                TechStack.Kotlin,
                TechStack.JetpackCompose,
                TechStack.ComposeMultiplatform,
            ),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/SimpleTopAppBar",
        ),
    ).sortedBy { it.repositoryUrl }

    fun getLibraries() = libraries
}
