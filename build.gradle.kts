repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    kotlin("multiplatform") version "1.6.10"
}

kotlin {
    macosX64("native") { // on macOS
        // linuxX64("native") // on Linux
        // mingwX64("native") // on Windows
        binaries {
            executable()
        }
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "6.7.1"
    distributionType = Wrapper.DistributionType.BIN
}


//task clean(type: Delete) {
//    delete rootProject.buildDir
//}