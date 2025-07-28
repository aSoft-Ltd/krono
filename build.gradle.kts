import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

@Suppress("DSL_SCOPE_VIOLATION") plugins {
    alias(androidx.plugins.library) apply false
    alias(kotlinz.plugins.multiplatform) apply false
    alias(kotlinz.plugins.serialization) apply false
    alias(asoft.plugins.library) apply false
    alias(vanniktech.plugins.maven.publish) apply false
    alias(kotlinz.plugins.dokka)
}

val v = asoft.versions.root.get()

group = "tz.co.asoft"
version = v

repositories {
	publicRepos()
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "com.vanniktech.maven.publish")

    val p = this
    version = v

    configure<MavenPublishBaseExtension> {
        publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL,automaticRelease = true)

        signAllPublications()

        coordinates("tz.co.asoft", p.name, v)

        pom {
            name.set(p.name)
            description.set(p.description ?: p.name)
            inceptionYear.set("2019")
            url.set("https://github.com/aSoft-Ltd/lexi")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://github.com/aSoft-Ltd/lexi/blob/master/LICENSE")
                }
            }
            developers {
                developer {
                    id.set("andylamax")
                    name.set("Anderson Lameck")
                    url.set("https://github.com/andylamax/")
                }
            }
            scm {
                url.set("https://github.com/aSoft-Ltd/lexi/")
                connection.set("scm:git:git://github.com/aSoft-Ltd/lexi.git")
                developerConnection.set("scm:git:ssh://git@github.com/aSoft-Ltd/lexi.git")
            }
        }
    }
}

tasks.dokkaHtmlMultiModule {
    moduleName.set("Lexi")
    outputDirectory.set(rootDir.resolve("docs"))
	moduleVersion.set(asoft.versions.root.get())
    includes.from("ReadMe.md")
}

group = "tz.co.asoft"
version = asoft.versions.root.get()