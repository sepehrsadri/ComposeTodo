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
include(":core:common")
include(":core:testing")
include(":core:datastore")
include(":feature:login")
include(":feature:todo")
include(":feature:splash")
include(":feature:profile")
