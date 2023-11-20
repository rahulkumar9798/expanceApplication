pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven ( url = "https://jitpack.io" )
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven ( url = "https://jitpack.io" )
        mavenCentral()
    }
}

rootProject.name = "Expance_Room_MVVM"
include(":app")
 