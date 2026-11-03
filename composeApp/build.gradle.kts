import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.aboutlibraries)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom(file("../detekt.yml"))
    // The plain detekt task only knows the default JVM layout (src/main/kotlin
    // etc.), which does not exist in a KMP project, so point it at all source
    // sets. Without this the detekt task analyzes no files at all.
    source.setFrom("src")
}

kotlin {
    compilerOptions {
        allWarningsAsErrors.set(true)
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.jetbrains.navigation3.ui)
            implementation(libs.jetbrains.material3.adaptiveNavigation3)
            implementation(libs.jetbrains.lifecycle.viewmodelNavigation3)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.aboutlibraries.compose)
            implementation(libs.kotlinInject.runtime)
            implementation(libs.yuyuyuyuyu.simpleTopAppBar)
            implementation(compose.materialIconsExtended)
            implementation(libs.yuyuyuyuyu.myMaterialTheme)
            implementation(libs.ktor.client.core)
            implementation(libs.markdownRenderer.m3)
            implementation(libs.markdownRenderer.coil3)
            implementation(libs.coil.networkKtor3)
            implementation(libs.coil.svg)
        }
        webMain.dependencies {
            implementation(libs.ktor.client.js)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
    }
}

dependencies {
    add("kspWasmJs", libs.kotlinInject.compiler)
}

ktlint {
    version =
        libs.versions.ktlint.cli
            .get()
    filter {
        val buildPath =
            layout.buildDirectory
                .get()
                .asFile.path + File.separator
        exclude { it.file.path.startsWith(buildPath) }
    }
}

aboutLibraries {
    export {
        outputFile = file("src/commonMain/composeResources/files/aboutlibraries.json")
    }
}

compose.resources {
    customDirectory(
        sourceSetName = "commonMain",
        directoryProvider =
            tasks
                .named("exportLibraryDefinitions")
                .map { layout.projectDirectory.dir("src/commonMain/composeResources") },
    )
}

val composeVersion = libs.versions.composeMultiplatform.get()
val material3Version = libs.versions.material3.get()
val material3AdaptiveVersion =
    libs.versions.compose.multiplatform.adaptive
        .get()

val composeLockstepGroups =
    setOf(
        "org.jetbrains.compose.animation",
        "org.jetbrains.compose.components",
        "org.jetbrains.compose.foundation",
        "org.jetbrains.compose.material",
        "org.jetbrains.compose.runtime",
        "org.jetbrains.compose.ui",
    )

dependencies.components.all {
    allVariants {
        withDependencies {
            forEach { dependency ->
                when (dependency.group) {
                    in composeLockstepGroups -> {
                        dependency.version { reject("($composeVersion,)") }
                    }

                    "org.jetbrains.compose.material3" -> {
                        dependency.version { reject("($material3Version,)") }
                    }

                    "org.jetbrains.compose.material3.adaptive" -> {
                        dependency.version { reject("($material3AdaptiveVersion,)") }
                    }
                }
            }
        }
    }
}
