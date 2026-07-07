plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.aboutlibraries) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false

    // applied (not `apply false`) because it contributes the version catalog
    // update tasks to this root project, where the catalog lives
    alias(libs.plugins.versionCatalogUpdate)
}

versionCatalogUpdate {
    // Keep the catalog sorted alphabetically by key. The plugin already sorts by
    // default, but we set it explicitly so the intent is documented in the build
    // and a canonical ordering keeps being enforced even if the plugin ever
    // changes its default.
    sortByKey.set(true)
}
