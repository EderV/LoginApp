pluginManagement {
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

rootProject.name = "LoginApp"
include(":app")
include(":lib:http-requests")
include(":lib:preferences")
include(":lib:entities")
include(":lib:strings")
include(":lib:comon")
