![Language](https://img.shields.io/github/languages/top/cortinico/kotlin-android-template?color=blue&logo=kotlin)

# CountryFinder 🌎

## Features 📋

This sample contains 2 screens: Home screen, a list of countries, where the user can view their
countries filtered by continent or language, and a detail screen which country information.

## Built With 🛠

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android
  development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s
  modern toolkit for building native UI.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - A coroutine is a
  concurrency design pattern that you can use on Android to simplify code that executes
  asynchronously.
- [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
  version of a Sequence, a type of collection whose values are lazily produced.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Collection of libraries that help you design robust, testable, and maintainable apps.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn"t destroyed on UI changes.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Lifecycle
      aware components perform actions in response to a change in the lifecycle status of another
      component, such as activities and fragments.
    - [Stateflow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow is a
      state-holder observable flow that emits the current and new state updates to its collectors.
    - [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
      version of a Sequence, a type of collection whose values are lazily produced.
    - [Jetpack Compose Navigation](https://developer.android.com/jetpack/compose/navigation) - The
      Navigation component provides support for Jetpack Compose applications.
- [Material Components for Android](https://github.com/material-components/material-components-android)
    - Modular and customizable Material Design UI components for Android.
- [Accompanist](https://github.com/material-components/material-components-android) - Accompanist is a
  group of libraries that aim to supplement Jetpack Compose with features that are commonly required
  by developers but not yet available.
- [Gson](https://github.com/google/gson) - Gson is a Java library that can be used to convert Java 
  Objects into their JSON representation. It can also be used to convert a JSON string to an 
  equivalent Java object. Gson can work with arbitrary Java objects including pre-existing objects 
  that you do not have source-code of.
- [Apollo Kotlin](https://github.com/apollographql/apollo-kotlin) - Apollo Kotlin (formerly known as
  Apollo Android) is a GraphQL client that generates Kotlin and Java models from GraphQL queries.Apollo
  Kotlin executes queries and mutations against a GraphQL server and returns results as query-specific
  Kotlin types.

## Architecture 🗼

I use MVVM adapted to Compose with Repository pattern and Clean Architecture general principles and
[SOLID principles](http://en.wikipedia.org/wiki/SOLID_%28object-oriented_design%29) can be
applied to Android development.

SOLID Components are an attempt at following good engineering standards and best practices such
as [SOLID](https://en.wikipedia.org/wiki/SOLID)
and [DRY](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself) where Google neglected to.

## UI tests ✅️
In [androidTest](app/src/androidTest/java/com/example/compose/jetchat) you'll find a suite of UI tests that
showcase interesting patterns in Compose:

#### [HomeScreenTest](app/src/androidTest/java/com/alessandrosisto/countryfinder/HomeScreenTest.kt)
UI tests for the home screen.

#### [DetailScreenTest](app/src/androidTest/java/com/alessandrosisto/countryfinder/DetailScreenTest.kt)
UI tests for the detail screen.

#### [ScreenshotComparatorTest](app/src/androidTest/java/com/alessandrosisto/countryfinder/ScreenshotComparatorTest.kt)
Screenshot test. Checks that the user input composable, including extended controls, behave as
expected showing and hiding the keyboard.