package dev.yuyuyuyuyu.portfolio.data.repositories

import dev.yuyuyuyuyu.portfolio.data.models.Product
import dev.yuyuyuyuyu.portfolio.data.models.TechStack
import me.tatarka.inject.annotations.Inject

@Inject
class LibrariesRepository {
    private val libraries = listOf(
        Product(
            name = "ngx-mat-third-party-licenses-list-view",
            description = "サードパーティライセンスのリストを提供するAngular Material用のコンポーネントライブラリ",
            techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.TypeScript),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-third-party-licenses-list-view",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            motivation = "今時のアプリに必須のオープンソースライセンス表示において、Angularの標準機能（thirdpartylicenses.txt）がSSR環境で参照できなくなる問題があった。新しいアプリを作るたびに実装をコピペしたり、修正のたびに全アプリに変更を反映して回るのが面倒だったためライブラリ化した。",
        ),
        Product(
            name = "@yuyuyuyuyu-dev/ngx-mat-my-simple-appbar",
            description = "Angular Material用のシンプルなアプリバー",
            techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.TypeScript),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-my-simple-appbar",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            motivation = "AngularでWebアプリを作る時、毎回同じようなアプリバー（ヘッダー）を作るのが面倒だったため、一度作って使い回せるようにライブラリ化した。",
        ),
        Product(
            name = "@yuyuyuyuyu-dev/ngx-mat-little-diva-theme",
            description = "This library is theme of little diva.",
            techStack = setOf(TechStack.Angular, TechStack.AngularMaterial, TechStack.Sass),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/ngx-mat-little-diva-theme",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            motivation = "せっかくアプリを作るなら独自色を出したいが、それを全アプリで毎回設定したりコピペしたりするのは面倒すぎて無理だったため、テーマ自体をライブラリとして切り出した。",
        ),
        Product(
            name = "RequestPermissions",
            description = "権限リクエストと処理の同時実行を実現するAndroid用ライブラリ",
            techStack = setOf(TechStack.Android, TechStack.Kotlin, TechStack.JetpackCompose),
            repositoryUrl = "https://github.com/yuyuyuyuyu-dev/RequestPermissions",
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Android),
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
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Android, dev.yuyuyuyuyu.portfolio.data.models.Platform.Ios, dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            motivation = "AndroidアプリでGoogleフォントを使う際、毎回公式ドキュメントからキーをコピペするのが面倒だった。また、フォントサイズごとにデフォルトを上書きする処理をアプリごとに書くなんて面倒すぎてやりたくなかったため、一度書けば使い回せるようにライブラリ化した。",
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
            category = dev.yuyuyuyuyu.portfolio.data.models.ProductCategory.Library,
            platforms = setOf(dev.yuyuyuyuyu.portfolio.data.models.Platform.Android, dev.yuyuyuyuyu.portfolio.data.models.Platform.Ios, dev.yuyuyuyuyu.portfolio.data.models.Platform.Web),
            motivation = "自分用のシンプルなアプリバー。アプリを作るごとに毎回同じようなUIコードを書くのは面倒だったため、コンポーネントとして共通化した。",
        ),
    ).sortedBy { it.repositoryUrl }

    fun getLibraries() = libraries
}
