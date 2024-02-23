import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar

private fun Project.subproject(suffix: String) : Project = project(":${rootProject.name}-$suffix")

val Project.projectApi
    get() = subproject("api")

val Project.projectCore
    get() = subproject("core")

val Project.projectPlugin
    get() = subproject("plugin")

private fun Project.coreTask(taskName: String) = projectCore.tasks.named(taskName, Jar::class.java)

val Project.coreDevJar
    get() = coreTask("coreDevJar")

val Project.coreReobfJar
    get() = coreTask("coreReobfJar")

val Project.coreSourcesJar
    get() = coreTask("sourcesJar")
