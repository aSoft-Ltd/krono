plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

description = "An implementation of the krono.api based on kotlinx"

kotlin {
    if (Targeting.JVM) jvm { library() }
    if (Targeting.JS) js(IR) { library() }
    if (Targeting.WASM) wasmJs { library() }
    if (Targeting.WASM) wasmWasi { library() }
    if (Targeting.OSX) osxTargets() else listOf()
    if (Targeting.NDK) ndkTargets() else listOf()
    if (Targeting.LINUX) linuxTargets() else listOf()
    if (Targeting.MINGW) mingwTargets() else listOf()

    sourceSets {
        commonMain.dependencies {
            api(projects.kronoApi)
            api(kotlinx.datetime)
        }

        commonTest.dependencies {
            implementation(libs.kommander.core)
            implementation(kotlinx.serialization.json)
        }

        jvmTest.dependencies {
            implementation(kotlin("test-junit5"))
        }
    }
}