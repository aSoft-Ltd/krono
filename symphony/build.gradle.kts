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
    if (Targeting.WASM) wasmJs { library() }
//    if (Targeting.WASM) wasmWasi { library() }
    if (Targeting.OSX) (iosTargets() + macOsTargets()) else listOf()
//    if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
//    if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.symphony.input.core)
                api(projects.kronoKotlinx)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kommander.coroutines)
                implementation(libs.koncurrent.later.coroutines)
                implementation(libs.cinematic.live.test)
                implementation(libs.koncurrent.executors.mock)
            }
        }
    }
}