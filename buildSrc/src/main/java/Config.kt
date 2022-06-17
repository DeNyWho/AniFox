object Config {
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"

    object Dependencies {
        const val kotlinPlugin = "gradle-plugin"
        const val androidPlugin = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlinter = "org.jmailen.gradle:kotlinter-gradle:${Versions.kotlinter}"
        const val dagger = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav}"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.serialization}"
    }

    object Repositories {
        const val gradleMaven = "https://plugins.gradle.org/m2/"
        const val jitpack = "https://jitpack.io"
    }

    object Plugins {
        const val android = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinAndroidExtensions = "kotlin-android-extensions"
        const val kotlinter = "org.jmailen.kotlinter"
        const val kapt = "kotlin-kapt"
        const val navigationSafeArgs = "androidx.navigation.safeargs"
        const val serialization = "kotlinx-serialization"
        const val dagger = "dagger.hilt.android.plugin"
    }

}