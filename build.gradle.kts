plugins {
    idea
    kotlin("jvm") version Versions.Kotlin
    id("org.jetbrains.dokka") version Versions.Dokka apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:${Versions.Paper}-R0.1-SNAPSHOT")

        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))
    }
}

listOf("api", "core").forEach { suffix ->
    project(":${rootProject.name}-$suffix") {
        apply(plugin = "org.jetbrains.dokka")

        tasks {
            create<Jar>("sourcesJar") {
                archiveClassifier.set("sources")
                from(sourceSets["main"].allSource)
            }

            create<Jar>("dokkaJar") {
                archiveClassifier.set("javadoc")
                dependsOn("dokkaHtml")
                from("${layout.buildDirectory.asFile.get().name}/dokka/html") {
                    include("**")
                }
            }
        }
    }
}

idea {
    module {
        excludeDirs.add(file(".server"))
        excludeDirs.addAll(allprojects.map { it.file(".gradle") })
    }
}
