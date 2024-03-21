enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "JoyFin"
include(":androidApp")
include(":common:logger")
include(":app-ui")
include(":app-presentation")
include(":common:domain")
include(":common:data")
include(":common:ios-data")
