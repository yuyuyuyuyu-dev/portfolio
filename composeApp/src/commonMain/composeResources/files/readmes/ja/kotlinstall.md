# kotlinstall

Goの`go install`やRustの`cargo install --git`のように、Kotlinで書かれたプログラムをGitHubのリポジトリから直接インストールするツール。

## 目次

- [作った理由](#作った理由)
- [できること](#できること)
- [使い方](#使い方)
- [制限事項](#制限事項)
- [ライセンス](#ライセンス)

## 作った理由

以前、起動の速い小さなコマンドラインツールが必要になり、Kotlin/NativeかRustかの二択になった。
Rustには、GitHubからプログラムを直接インストールできる`cargo install --git https://github.com/user/repository.git`がある。Kotlinにはそのようなものがなかったので、Rustを選んだ。

それでもKotlinが好きで諦めきれなかったので、kotlinstallを作った。

## できること

Kotlinで書かれたプログラムをGitHubから直接インストールする。
ソースコードを一時ディレクトリにクローンしてビルドし、その成果物を`~/.kotlinstall/packages/<パッケージ名>/`にコピーして、`~/.kotlinstall/bin/<コマンド名>`からシンボリックリンクを張り、最後に一時ディレクトリを丸ごと削除する。

## 使い方

### 必要なもの

- macOSまたはLinux
- Git
- JDK。インストーラースクリプトと`kotlinstall install`がプログラムをビルドするのに使う。JDK 25で動作を確認している。
- Xcode（macOSのみ）。Command Line Toolsだけでも足りるかもしれないが、試していない。
- `kotlinr`コマンドを含むKotlinコンパイラー（インストーラースクリプトを実行する場合のみ）

### kotlinstallをインストールする

```bash
curl -fsSL https://raw.githubusercontent.com/yuyuyuyuyu-dev/kotlinstall/main/install-kotlinstall.main.kts | kotlinr -howtorun .main.kts /dev/stdin
```

インストーラースクリプトはkotlinstallをソースコードからビルドし、`~/.kotlinstall/bin`に配置する。このディレクトリを`PATH`に追加する:

```bash
export PATH="$HOME/.kotlinstall/bin:$PATH"
```

### プログラムをインストールする

```bash
kotlinstall install https://github.com/user/repository.git
```

プログラムのコマンドが`~/.kotlinstall/bin`にインストールされる。

### その他のコマンド

ヘルプを参照:

```bash
kotlinstall --help
```

### kotlinstallをアンインストールする

kotlinstallがインストールするものは、kotlinstall自身も含めてすべて`~/.kotlinstall`にある。このディレクトリを削除すると、kotlinstallとそれがインストールしたすべてのプログラムが削除される:

```bash
rm -rf ~/.kotlinstall
```

その後、`PATH`から`~/.kotlinstall/bin`を取り除く。

GradleとKotlin/Nativeは、ダウンロードした依存関係やコンパイラーなどの自身のファイルを`~/.gradle`と`~/.konan`に置いている。これらは他のツールも使うので、kotlinstallは手を付けない。

## 制限事項

対応しているのはGradleプロジェクトのみで、Kotlin Toolchainのプロジェクトには対応していない。
Kotlin Toolchainはまだアルファ版で、その`kotlin`コマンドはKotlinコンパイラーの`kotlin`コマンドと同じ名前になっている。対応するには、Kotlin Toolchainが入ったマシンでインストーラースクリプトをどう実行してもらうかを考える必要がある。それを考える気になれなかったので、この最初のバージョンではKotlin Toolchainを対象外にした。

また、対応しているのはKotlin/Nativeのプログラムのみで、JVM上で動くプログラムには対応していない。
対応するには、プログラムが必要とするバージョンのJavaがインストールされていない場合にどうするかを考える必要がある。それも考える気になれなかったので、この最初のバージョンでは対象外にした。

## ライセンス

[MIT](LICENSE)
