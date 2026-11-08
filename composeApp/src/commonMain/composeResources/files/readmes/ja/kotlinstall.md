# kotlinstall

Goの`go install`やRustの`cargo install --git`のように、Kotlinで書かれたプログラムをGitHubのリポジトリから直接インストールするツールです。

## 目次

- [なぜ作ったのか](#なぜ作ったのか)
- [できること](#できること)
- [使い方](#使い方)
- [制限事項](#制限事項)
- [ライセンス](#ライセンス)

## なぜ作ったのか

以前、起動の速い小さなコマンドラインツールが必要になり、Kotlin/NativeかRustかの二択になりました。
Rustには、GitHubからプログラムを直接インストールできる`cargo install --git https://github.com/user/repository.git` があります。Kotlinにはそのようなものがなかったので、Rustを選びました。

それでもKotlinが好きで諦めきれなかったので、kotlinstallを作りました。

## できること

Kotlinで書かれたプログラムをGitHubから直接インストールします。
ソースコードを一時ディレクトリにクローンしてビルドし、その成果物を`~/.kotlinstall/packages/<パッケージ名>/`にコピーして、`~/.kotlinstall/bin/<コマンド名>`からシンボリックリンクを張り、最後に一時ディレクトリを丸ごと削除します。

## 使い方

### 必要なもの

- macOSまたはLinux
- Git
- JDK。インストーラースクリプトと`kotlinstall install`がプログラムをビルドするのに使います。JDK 25で動作を確認しています。
- Xcode（macOSのみ）。Command Line Toolsだけでも足りるかもしれませんが、試していません。
- `kotlinr`コマンドを含むKotlinコンパイラー（インストーラースクリプトを実行する場合のみ）

### kotlinstallをインストールする

```bash
curl -fsSL https://raw.githubusercontent.com/yuyuyuyuyu-dev/kotlinstall/main/install-kotlinstall.main.kts | kotlinr -howtorun .main.kts /dev/stdin
```

インストーラースクリプトはkotlinstallをソースコードからビルドし、`~/.kotlinstall/bin`に配置します。このディレクトリを`PATH`に追加してください:

```bash
export PATH="$HOME/.kotlinstall/bin:$PATH"
```

### プログラムをインストールする

```bash
kotlinstall install https://github.com/user/repository.git
```

プログラムのコマンドが`~/.kotlinstall/bin`にインストールされます。

### その他のコマンド

ヘルプを参照してください:

```bash
kotlinstall --help
```

### kotlinstallをアンインストールする

kotlinstallがインストールするものは、kotlinstall自身も含めてすべて`~/.kotlinstall`にあります。このディレクトリを削除すると、kotlinstallとそれがインストールしたすべてのプログラムが削除されます:

```bash
rm -rf ~/.kotlinstall
```

その後、`PATH`から`~/.kotlinstall/bin`を取り除いてください。

GradleとKotlin/Nativeは、ダウンロードした依存関係やコンパイラーなどの自身のファイルを`~/.gradle`と`~/.konan`に置いています。これらは他のツールも使うので、kotlinstallは手を付けません。

## 制限事項

対応しているのはGradleプロジェクトのみで、Kotlin Toolchainのプロジェクトには対応していません。
Kotlin Toolchainはまだアルファ版で、その`kotlin`コマンドはKotlinコンパイラーの`kotlin`コマンドと同じ名前になっています。対応するには、Kotlin Toolchainが入ったマシンでインストーラースクリプトをどう実行してもらうかを考える必要があります。それを考える気になれなかったので、この最初のバージョンではKotlin Toolchainを対象外にしました。

また、対応しているのはKotlin/Nativeのプログラムのみで、JVM上で動くプログラムには対応していません。
対応するには、プログラムが必要とするバージョンのJavaがインストールされていない場合にどうするかを考える必要があります。それも考える気になれなかったので、この最初のバージョンでは対象外にしました。

## ライセンス

[MIT](LICENSE)
