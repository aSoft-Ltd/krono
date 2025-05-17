pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("../versions/gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
            }
        }
    }
}

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "krono"

listOf(
    "kase", "kommander", "cinematic",
    "koncurrent", "kevlar", "lexi", "symphony",
    "kollections", "kotlinx-interoperable"
).forEach {
    includeBuild("../$it")
}

includeBuild("../able")

//includeSubs("krono", ".", "api", "kotlinx", "symphony")
includeSubs("krono", ".", "api", "kotlinx")