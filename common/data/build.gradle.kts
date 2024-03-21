plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data"
            isStatic = true
        }
    }

    val ktorVersion = "2.3.9"

    sourceSets {
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
        }

        commonMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.3")
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

            api(project(":common:domain"))
            api(project(":common:logger"))

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        }
    }
}

android {
    namespace = "com.teewhydope.app.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin.targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
    binaries.all {
        freeCompilerArgs += "-Xdisable-phases=EscapeAnalysis"
    }
}

task("testClasses").doLast {
    //workaround for failing build
}
