rootProject.name = "matrix"

val prefix = rootProject.name

include(
    "$prefix-api",
    "$prefix-core",
    "$prefix-plugin"
)

val version = "$prefix-version"
val versionFile = file(version)
if (versionFile.exists()) {
    include(version)
    versionFile.listFiles()?.filter { file ->
        file.isDirectory && file.startsWith("v")
    }?.forEach { specificVersion ->
        include(":$version:$specificVersion")
    }
}
