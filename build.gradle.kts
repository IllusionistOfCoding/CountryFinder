// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val gradleVersion: String by project
    val kotlinVersion: String by project
    val apolloVersion: String by project

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:$gradleVersion")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.apollographql.apollo:apollo-gradle-plugin:$apolloVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}