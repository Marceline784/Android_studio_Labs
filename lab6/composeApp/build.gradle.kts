import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    // KOIN
    alias(libs.plugins.koin.compiler)
}

kotlin {

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    sourceSets {

        androidMain.dependencies {

            implementation(libs.compose.uiToolingPreview)

            implementation(libs.androidx.activity.compose)

            // KOIN
            implementation(libs.koin.android)
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

            implementation(libs.napier)

            // KOIN
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // MULTIPLATFORM SETTINGS
            implementation(libs.multiplatform.settings)

            implementation(libs.kotlinx.serialization)

            implementation(libs.multiplatform.settings.serialization)

            implementation(libs.kotlinx.coroutines.core)

            implementation(libs.multiplatform.settings.coroutines)
            implementation(libs.kotlinx.datetime)
        }

        commonTest.dependencies {

            implementation(libs.kotlin.test)

            implementation(libs.koin.test)
        }

        jvmMain.dependencies {

            implementation(compose.desktop.currentOs)

            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}

android {

    namespace = "com.example.lab6"

    compileSdk =
        libs.versions.android.compileSdk.get().toInt()

    defaultConfig {

        applicationId = "com.example.lab6"

        minSdk =
            libs.versions.android.minSdk.get().toInt()

        targetSdk =
            libs.versions.android.targetSdk.get().toInt()

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

dependencies {

    debugImplementation(libs.compose.uiTooling)
}

compose.desktop {

    application {

        mainClass = "com.example.lab6.MainKt"

        nativeDistributions {

            targetFormats(
                TargetFormat.Dmg,
                TargetFormat.Msi,
                TargetFormat.Deb
            )

            packageName = "com.example.lab6"

            packageVersion = "1.0.0"
        }
    }
}