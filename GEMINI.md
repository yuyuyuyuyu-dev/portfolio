# Development Rules

- After making code changes, you must always run the formatters:
  - Kotlin: `./gradlew ktlintFormat`
  - Everything else: `npm run biome -- check --write`
