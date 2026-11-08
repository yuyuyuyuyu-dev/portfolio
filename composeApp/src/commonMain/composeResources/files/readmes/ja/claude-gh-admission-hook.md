# claude-gh-admission-hook

[![claude-gh-admission-hook specs](https://github.com/yuyuyuyuyu-dev/dotfiles/actions/workflows/claude-gh-admission-hook-specs.yml/badge.svg)](https://github.com/yuyuyuyuyu-dev/dotfiles/actions/workflows/claude-gh-admission-hook-specs.yml)

Claude CodeがGitHubにアクセスする方法を制限するフックです。

## 目次

- [なぜ作ったのか](#なぜ作ったのか)
- [できること](#できること)
- [使い方](#使い方)
- [ライセンス](#ライセンス)

## なぜ作ったのか

Claude Codeがデフォルトでオートモードで起動するようになり、ローカルファイルへの意図しない変更をすべて防ぐことはもう期待しなくなりました。そこで、ローカルではそのリスクを受け入れ、代わりにリモート側をしっかり守ることにしました。

オンラインストレージには自分のコマンドラインから届かないので、もともと問題になりませんでした。残る問題は`gh`コマンドを通じたGitHubへのアクセスだけでした。そこでこのフックを書きました。

## できること

`~/.claude/settings.json`に登録して使うフックです。`gh`コマンドを通じたGitHubへのアクセスを読み取り専用に保ち、許可している数少ない書き込み（ドラフトのプルリクエストの作成と編集、ドラフトのリリースの作成と編集）の前には確認を求めます。プルリクエストにはトピックブランチが必要なので、トピックブランチへのプッシュは許可しています。

タグの作成も拒否します。タグは自分の知らないところでリリースワークフローを開始させることがあるからです。（プッシュだけを止めるのは難しかったので、代わりに作成を止めました。）

また、`gh`から取り出したトークンを使うものも含め、`curl`や`wget`でGitHub APIに直接送られる書き込みも拒否します。

## 使い方

### 必要なもの

- [kotlinstall](https://github.com/yuyuyuyuyu-dev/kotlinstall)

### フックをインストールする

```bash
kotlinstall install https://github.com/yuyuyuyuyu-dev/dotfiles.git --subdirectory=claude-gh-admission-hook
```

### フックを登録する

インストールしたら、`~/.claude/settings.json`に`PreToolUse`フックとして登録してください。Claude Codeはこのファイルの現在の構造についてのドキュメントを持っているようなので、Claude Codeに頼むのがおそらく一番簡単です。

## ライセンス

[MIT](../LICENSE)
