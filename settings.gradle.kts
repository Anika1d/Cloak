enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Cloak"
include(
    ":instances:app",
    ":components:singleactivity",
    ":datastore",
    "core:data",
    "core:service",
    "core:repository",
    "core:ktor",
    "core:usecase",
)
 