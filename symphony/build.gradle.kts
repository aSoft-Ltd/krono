plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("org.jetbrains.dokka")
    signing
}

description = "A kotlin multiplatform library"

kotlin {
    jvm { library() }
    if (Targeting.JS) js(IR) { library() }
//    if (Targeting.WASM) wasm { library() }
    val osxTargets = if (Targeting.OSX) osxTargets() else listOf()
//    val ndkTargets = if (Targeting.NDK) ndkTargets() else listOf()
    val linuxTargets = if (Targeting.LINUX) linuxTargets() else listOf()
//    val mingwTargets = if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.symphonyInputsCore)
                api(projects.kronoKotlinx)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(projects.kommanderCoroutines)
                implementation(projects.koncurrentLaterCoroutines)
                implementation(projects.cinematicLiveTest)
                implementation(projects.koncurrentExecutorsMock)
            }
        }
    }
}