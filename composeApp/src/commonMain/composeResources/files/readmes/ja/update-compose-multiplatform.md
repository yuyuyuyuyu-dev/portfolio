# update-compose-multiplatform

Compose Multiplatformとその関連ライブラリを、正しい組み合わせで更新する再利用可能なワークフロー。

## 目次

- [作った理由](#作った理由)
- [できること](#できること)
- [使い方](#使い方)
- [ライセンス](#ライセンス)

## 作った理由

自作のCompose MultiplatformアプリをDependabotで保守していたところ、各ライブラリを単純に最新バージョンへ上げるだけではうまくいかないことを知った。Compose Multiplatformの周辺ライブラリのバージョンは、Compose Multiplatform本体に合わせて選ぶ必要がある。更新のたびに手作業で合わせるのが面倒だったので、そのためだけのワークフローを作った。

## できること

GitHub上のCompose Multiplatformリポジトリにある[CHANGELOG](https://github.com/JetBrains/compose-multiplatform/blob/master/CHANGELOG.md)を読み、そこに載っている最新の安定版に合わせてバージョンカタログを更新し、プルリクエストを作成する。オプションで、yarn.lockの更新と自動マージの有効化もできる。

## 使い方

### 前提条件

- リポジトリのコンテンツとプルリクエストへの読み取り・書き込み権限を持ち、そのリポジトリにインストールされたGitHub App。ワークフローはこのアプリとしてプルリクエストを作成する。
- リポジトリの変数として保存した、アプリのクライアントID
- リポジトリのシークレットとして保存した、アプリの秘密鍵

例えばDependabotのプルリクエストで自動マージを有効にするためのアプリなど、既にそのようなアプリがあれば、ここでも使える。これらを用意するには、GitHubのドキュメントの[Authenticating with a GitHub App](https://docs.github.com/en/apps/creating-github-apps/authenticating-with-a-github-app/making-authenticated-api-requests-with-a-github-app-in-a-github-actions-workflow#authenticating-with-a-github-app)に従う。

### インストール

次のファイルを`.github/workflows/update-compose-multiplatform.yml`としてリポジトリに追加する:

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

ファイル内の次の部分を置き換える:

- `<version>`を、使用するこのワークフローのバージョン（`v1`など）に置き換える。
- `<PULL_REQUEST_APP_CLIENT_ID>`と`<PULL_REQUEST_APP_PRIVATE_KEY>`を、自分の変数とシークレットの名前に置き換える。

このファイルは毎日UTC 7:31にワークフローを実行する。別の時刻に実行するには、[GitHub Actionsのドキュメント](https://docs.github.com/en/actions/reference/workflows-and-actions/events-that-trigger-workflows#schedule)でスケジュールの書き方を参照する。各実行では、更新があればプルリクエストを作成する。

### オプション

yarn.lockの更新や自動マージの有効化も行うには、ファイルの`with`の下に入力を追加する。すべてのオプションを有効にすると、その部分は次のようになる:

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

| 名前                                   | デフォルト | 説明                                                                                                                                                        |
| -------------------------------------- | ---------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `update-js-yarn-lock`                  | `false`    | `true`にすると、`./gradlew kotlinUpgradeYarnLock`で`kotlin-js-store/yarn.lock`を更新する。                                                                  |
| `update-wasm-yarn-lock`                | `false`    | `true`にすると、`./gradlew kotlinWasmUpgradeYarnLock`で`kotlin-js-store/wasm/yarn.lock`を更新する。                                                         |
| `jdk-distribution-to-update-yarn-lock` |            | Gradleを実行するJDKのディストリビューション。[actions/setup-java](https://github.com/actions/setup-java)での名前で指定する。yarn.lockを更新する場合は必須。 |
| `jdk-version-to-update-yarn-lock`      |            | Gradleを実行するJDKのバージョン。yarn.lockを更新する場合は必須。                                                                                            |
| `app-client-id`                        |            | プルリクエストを作成するGitHub AppのクライアントID。`app-private-key`シークレットと一緒に渡す。                                                             |
| `auto-merge`                           | `disable`  | 自動マージを有効にする際のマージ方法。`squash`、`merge`、`rebase`のいずれか。`disable`の場合は自動マージを有効にしない。                                    |
| `auto-merge-update-types`              |            | 自動マージを有効にするCompose Multiplatformの更新の種類。`major`、`minor`、`patch`をカンマ区切りで指定する。`auto-merge`が`disable`でない場合は必須。       |

### シークレット

| 名前              | 説明                                                                          |
| ----------------- | ----------------------------------------------------------------------------- |
| `app-private-key` | プルリクエストを作成するGitHub Appの秘密鍵。`app-client-id`入力と一緒に渡す。 |

## ライセンス

[MIT](LICENSE)
