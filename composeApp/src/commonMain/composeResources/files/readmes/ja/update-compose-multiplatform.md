# update-compose-multiplatform

Compose Multiplatformとその関連ライブラリを、正しい組み合わせで更新する再利用可能なワークフローです。

## 目次

- [なぜ作ったのか](#なぜ作ったのか)
- [できること](#できること)
- [使い方](#使い方)
- [ライセンス](#ライセンス)

## なぜ作ったのか

自作のCompose MultiplatformアプリをDependabotで保守していたところ、各ライブラリを単純に最新バージョンへ上げるだけではうまくいかないことを知りました。Compose Multiplatformの周辺ライブラリのバージョンは、Compose Multiplatform本体に合わせて選ぶ必要があります。更新のたびに手作業で合わせるのが面倒だったので、そのためだけのワークフローを作りました。

## できること

GitHub上のCompose Multiplatformリポジトリにある[CHANGELOG](https://github.com/JetBrains/compose-multiplatform/blob/master/CHANGELOG.md)を読み、そこに載っている最新の安定版に合わせてバージョンカタログを更新し、プルリクエストを作成します。オプションで、yarn.lockの更新と自動マージの有効化もできます。

## 使い方

### 前提条件

- リポジトリのコンテンツとプルリクエストへの読み取り・書き込み権限を持ち、そのリポジトリにインストールされたGitHub App。ワークフローはこのアプリとしてプルリクエストを作成します。
- リポジトリの変数として保存した、アプリのクライアントID
- リポジトリのシークレットとして保存した、アプリの秘密鍵

例えばDependabotのプルリクエストで自動マージを有効にするためのアプリなど、既にそのようなアプリがあれば、ここでも使えます。これらを用意するには、GitHubのドキュメントの[Authenticating with a GitHub App](https://docs.github.com/en/apps/creating-github-apps/authenticating-with-a-github-app/making-authenticated-api-requests-with-a-github-app-in-a-github-actions-workflow#authenticating-with-a-github-app)に従ってください。

### インストール

次のファイルを`.github/workflows/update-compose-multiplatform.yml`としてリポジトリに追加してください:

```yaml
name: Update Compose Multiplatform
on:
  schedule:
    - cron: "31 7 * * *"
  workflow_dispatch:
permissions: {}
jobs:
  update-compose-multiplatform:
    permissions:
      contents: read
    uses: yuyuyuyuyu-dev/update-compose-multiplatform/.github/workflows/update-compose-multiplatform.yml@<version>
    with:
      app-client-id: ${{ vars.<PULL_REQUEST_APP_CLIENT_ID> }}
    secrets:
      app-private-key: ${{ secrets.<PULL_REQUEST_APP_PRIVATE_KEY> }}
```

ファイル内の次の部分を置き換えてください:

- `<version>`を、使用するこのワークフローのバージョン（`v1`など）に置き換えます。
- `<PULL_REQUEST_APP_CLIENT_ID>`と`<PULL_REQUEST_APP_PRIVATE_KEY>`を、自分の変数とシークレットの名前に置き換えます。

このファイルは毎日UTC 7:31にワークフローを実行します。別の時刻に実行するには、[GitHub Actionsのドキュメント](https://docs.github.com/en/actions/reference/workflows-and-actions/events-that-trigger-workflows#schedule)でスケジュールの書き方を参照してください。各実行では、更新があればプルリクエストを作成します。

### オプション

yarn.lockの更新や自動マージの有効化も行うには、ファイルの`with`の下に入力を追加します。すべてのオプションを有効にすると、その部分は次のようになります:

```yaml
with:
  update-js-yarn-lock: true
  update-wasm-yarn-lock: true
  jdk-distribution-to-update-yarn-lock: zulu
  jdk-version-to-update-yarn-lock: "21"
  app-client-id: ${{ vars.<PULL_REQUEST_APP_CLIENT_ID> }}
  auto-merge: squash
  auto-merge-update-types: minor,patch
```

### 入力

| 名前                                   | デフォルト | 説明                                                                                                                                                              |
| -------------------------------------- | ---------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `update-js-yarn-lock`                  | `false`    | `true`にすると、`./gradlew kotlinUpgradeYarnLock`で`kotlin-js-store/yarn.lock`を更新します。                                                                      |
| `update-wasm-yarn-lock`                | `false`    | `true`にすると、`./gradlew kotlinWasmUpgradeYarnLock`で`kotlin-js-store/wasm/yarn.lock`を更新します。                                                             |
| `jdk-distribution-to-update-yarn-lock` |            | Gradleを実行するJDKのディストリビューション。[actions/setup-java](https://github.com/actions/setup-java)での名前で指定します。yarn.lockを更新する場合は必須です。 |
| `jdk-version-to-update-yarn-lock`      |            | Gradleを実行するJDKのバージョン。yarn.lockを更新する場合は必須です。                                                                                              |
| `app-client-id`                        |            | プルリクエストを作成するGitHub AppのクライアントID。`app-private-key`シークレットと一緒に渡します。                                                               |
| `auto-merge`                           | `disable`  | 自動マージを有効にする際のマージ方法。`squash`、`merge`、`rebase`のいずれかです。`disable`の場合は自動マージを有効にしません。                                    |
| `auto-merge-update-types`              |            | 自動マージを有効にするCompose Multiplatformの更新の種類。`major`、`minor`、`patch`をカンマ区切りで指定します。`auto-merge`が`disable`でない場合は必須です。       |

### シークレット

| 名前              | 説明                                                                              |
| ----------------- | --------------------------------------------------------------------------------- |
| `app-private-key` | プルリクエストを作成するGitHub Appの秘密鍵。`app-client-id`入力と一緒に渡します。 |

## ライセンス

[MIT](LICENSE)
