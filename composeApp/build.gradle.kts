import com.google.devtools.ksp.gradle.KspAATask
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
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

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    js {
        browser()
        binaries.executable()
    }

    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }

        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
        }
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
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
    }
}

android {
    namespace = "dev.yuyuyuyuyu.portfolio"
    compileSdk =
        providers
            .gradleProperty("android.compileSdk")
            .get()
            .toInt()

    defaultConfig {
        applicationId = "dev.yuyuyuyuyu.portfolio"
        minSdk =
            providers
                .gradleProperty("android.minSdk")
                .get()
                .toInt()
        targetSdk =
            providers
                .gradleProperty("android.targetSdk")
                .get()
                .toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "dev.yuyuyuyuyu.portfolio.MainKt"
    }
}

dependencies {
    kspCommonMainMetadata(libs.kotlinInject.compiler)
    debugImplementation(libs.compose.uiTooling)
}

tasks.withType<KspAATask>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

tasks.matching { it.name.contains("Ktlint", ignoreCase = true) }.configureEach {
    dependsOn("kspCommonMainKotlinMetadata")
}

ktlint {
    filter {
        exclude { it.file.path.contains("build/generated/") }
    }
}
