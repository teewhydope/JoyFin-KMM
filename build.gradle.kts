plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    kotlin("plugin.serialization") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
    alias(libs.plugins.ksp).apply(false)
}

buildscript {
    dependencies {
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)

    }
}
