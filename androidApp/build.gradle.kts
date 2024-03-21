plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.teewhydope.app.joyfin.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.teewhydope.app.joyfin.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.androidx.nav.ui.ktx)
    implementation(libs.androidx.nav.fragment.ktx)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.ktor.client.okhttp)

    implementation(project(":common:logger"))
    implementation(project(":app-ui"))
    implementation(project(":app-presentation"))
    implementation(project(":common:domain"))
    implementation(project(":common:data"))
}