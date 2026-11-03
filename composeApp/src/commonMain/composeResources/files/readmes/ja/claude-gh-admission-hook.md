# claude-gh-admission-hook

[![claude-gh-admission-hook specs](https://github.com/yuyuyuyuyu-dev/dotfiles/actions/workflows/claude-gh-admission-hook-specs.yml/badge.svg)](https://github.com/yuyuyuyuyu-dev/dotfiles/actions/workflows/claude-gh-admission-hook-specs.yml)

Claude CodeがGitHubにアクセスする方法を制限するフック。

## 目次

- [作った理由](#作った理由)
- [できること](#できること)
- [使い方](#使い方)
- [ライセンス](#ライセンス)

## 作った理由

Claude Codeがデフォルトでオートモードで起動するようになり、ローカルファイルへの意図しない変更をすべて防ぐことはもう期待しなくなった。そこで、ローカルではそのリスクを受け入れ、代わりにリモート側をしっかり守ることにした。

オンラインストレージには自分のコマンドラインから届かないので、もともと問題にならなかった。残る問題は`gh`コマンドを通じたGitHubへのアクセスだけだった。そこでこのフックを書いた。

## できること

`~/.claude/settings.json`に登録して使うフック。`gh`コマンドを通じたGitHubへのアクセスを読み取り専用に保ち、許可している数少ない書き込み（ドラフトのプルリクエストの作成と編集、ドラフトのリリースの作成と編集）の前には確認を求める。プルリクエストにはトピックブランチが必要なので、トピックブランチへのプッシュは許可している。

タグの作成も拒否する。タグは自分の知らないところでリリースワークフローを開始させることがあるからだ。（プッシュだけを止めるのは難しかったので、代わりに作成を止めた。）

また、`gh`から取り出したトークンを使うものも含め、`curl`や`wget`でGitHub APIに直接送られる書き込みも拒否する。

## 使い方

### 必要なもの

- [kotlinstall](https://github.com/yuyuyuyuyu-dev/kotlinstall)

### フックをインストールする

```bash
kotlinstall install https://github.com/yuyuyuyuyu-dev/dotfiles.git --subdirectory=claude-gh-admission-hook
```

### フックを登録する

インストールしたら、`~/.claude/settings.json`に`PreToolUse`フックとして登録する。Claude Codeはこのファイルの現在の構造についてのドキュメントを持っているようなので、Claude Codeに頼むのがおそらく一番簡単だ。

## ライセンス

[MIT](../LICENSE)
