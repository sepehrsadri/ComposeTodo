pluginManagement {
  repositories {
    mavenCentral()
    gradlePluginPortal()
    google()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    google()
  }
}

rootProject.name = "ComposeMockTodo"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:network")
include(":core:model")
include(":core:data")
include(":core:designsystem")
include(":core:domain")
include(":core:logger")
include(":feature:login")
