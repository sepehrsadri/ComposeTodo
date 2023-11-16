# ComposeMockTodo

The `ComposeMockTodo` consuming
a [Api](https://jsonplaceholder.typicode.com/) to display todo list. it
has been built with `Modular` and `Clean Architecture` principles, Repository Pattern, and MVVM
pattern as well as Architecture Components also
utilized with `Android Jetpack` contains: Compose, Hilt and ...

This app shows the usage of the new Android Jetpack and Architecture Components.

**App features:**

- Login
- List of todo items
- Light / Dark theme switch


## UI
The app was designed using [Material 3 guidelines](https://m3.material.io/).

The Screens and UI elements are built entirely using [Jetpack Compose](https://developer.android.com/jetpack/compose).

## Testing
This project has been covered with
some [Local Unit Test](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
to passing view model, repository and useCases.

To facilitate testing of components uses dependency injection with
[Hilt](https://developer.android.com/training/dependency-injection/hilt-android).

Most data layer components are defined as interfaces.
Then, concrete implementations (with various dependencies) are bound to provide those interfaces to
other components in the app.
In tests, notably does _not_ use any mocking libraries.
Instead, the production implementations can be replaced with test doubles using Hilt's testing APIs
(or via manual constructor injection for `ViewModel` tests).

## Build

The app contains the usual `debug` and `release` build variants.

## Modularization

The app has been fully modularized and you can find the detailed guidance and
description of the modularization strategy used in
[modularization learning journey](docs/ModularizationLearningJourney.md).

* **app** - It uses all the components and classes related to Android Framework. It gets the data
  from other modules and shows on UI. (**access all the modules**)
* **feature-login** - this feature module contains login screen.
* **features-todo** - this feature module contains showing todo items screen.
* **core** - contains different modules such as **core:common**, **core:data**, **core:
  designsystem**, **core:domain**, **core:logger**, **core:network**, **core:testing** and ...
  which contains business logic of application.



## Architecture

The app follows the
[official architecture guidance](https://developer.android.com/topic/architecture)
and is described in detail in the
[architecture learning journey](docs/ArchitectureLearningJourney.md).

Uses concepts of the notorious Uncle Bob's architecture
called [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
.

* Better separation of concerns. Each module has a clear API., Feature related classes life in
  different modules and can't be referenced without explicit module dependency.
* Features can be developed in parallel eg. by different teams
* Each feature can be developed in isolation, independently from other features
* faster compile time

## Tech stack - Library:

- [Kotlin](https://kotlinlang.org/) - Kotlin is a cross-platform, statically typed, general-purpose
  programming language with type inference. Kotlin is designed to interoperate fully with Java, and
  the JVM version of Kotlin's standard library depends on the Java Class Library, but type inference
  allows its syntax to be more concise.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - A coroutine is a concurrency design
  pattern that you can use on Android to simplify code that executes asynchronously
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - for
  dependency injection.
- JetPack
    - [Compose]("https://developer.android.com/jetpack/compose") - Jetpack Compose is Android’s
      recommended modern toolkit for building native UI. It simplifies and accelerates UI
      development on Android. Quickly bring your app to life with less code, powerful tools, and
      intuitive Kotlin APIs.
    - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Used get
      lifecyle event of an activity or fragment and performs some action in response to change
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn't destroyed on UI changes.
- [Material-Components](https://github.com/material-components/material-components-android) -
  Material design components.
- [Retrofit](https://github.com/square/retrofit) - Used for REST api communication.
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin serialization consists of a compiler plugin, that generates visitor code for serializable classes, runtime library with core serialization API and support libraries with various serialization formats.
- [Timber]("https://github.com/JakeWharton/timber") - This is a logger with a small, extensible API
  which provides utility on top of Android's normal Log class.
- other libraries...


## Future Road Map

- Add profile screen
- Save user login state
- UI/UX improvements
- more features to be implemented


