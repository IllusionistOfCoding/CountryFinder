plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.apollographql.apollo")
}

val composeVersion: String by project
val apolloVersion: String by project
val gsonVersion: String by project
val coroutinesVersion: String by project
val accompanistVersion: String by project
val lifecycleVersion: String by project

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.alessandrosisto.countryfinder"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    apollo {
        generateKotlinModels.set(true)
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.activity:activity-compose:1.4.0")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.4.1")

    // Accompanist
    implementation("com.google.accompanist:accompanist-insets:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    // Gson
    implementation("com.google.code.gson:gson:$gsonVersion")

    // Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")

    // Apollo
    implementation("com.apollographql.apollo:apollo-runtime:$apolloVersion")
    implementation("com.apollographql.apollo:apollo-coroutines-support:$apolloVersion")

    // Testing
    testImplementation("junit:junit:4.13.2")
    //For InstantTaskExecutorRule
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    //For runBlockingTest, CoroutineDispatcher etc.
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    // Needed for createComposeRule, but not createAndroidComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
}