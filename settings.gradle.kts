pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Alfath"
include(":app")
include(":core:ui")
include(":core:domain")
include(":core:data")
include(":features:home:ui")
include(":features:profile:ui")
include(":navigation:api")
include(":navigation:impl")
include(":infrastructure:database")
include(":infrastructure:network")
