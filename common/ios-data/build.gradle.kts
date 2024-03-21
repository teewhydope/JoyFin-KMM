plugins {
    alias(libs.plugins.kotlinMultiplatform)
}


kotlin {

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "IosData"
            isStatic = true

            export(project(":common:logger"))
            export(project(":common:data"))
            export(project(":common:domain"))
        }
    }

    sourceSets {
        commonMain.dependencies {
        }

        iosMain.dependencies {
            api(project(":common:logger"))
            api(project(":common:data"))
            api(project(":common:domain"))
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }

    task("testClasses").doLast {
        //workaround for failing build
    }
}



