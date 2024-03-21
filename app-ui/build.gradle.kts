plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    alias(libs.plugins.ksp)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.teewhydope.app.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.androidx.nav.ui.ktx)
    implementation(libs.androidx.nav.fragment.ktx)

    implementation(libs.rxkotlin)


    api(project(":app-presentation"))

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation("androidx.recyclerview:recyclerview:1.3.2")

}