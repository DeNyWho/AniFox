buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri(Config.Repositories.gradleMaven) }
        maven { url = uri(Config.Repositories.jitpack) }
    }
    dependencies {
        classpath(Config.Dependencies.androidPlugin)
        classpath(kotlin(Config.Dependencies.kotlinPlugin, Versions.kotlin))
        classpath(Config.Dependencies.kotlinter)
        classpath(Config.Dependencies.serialization)
        classpath(Config.Dependencies.navigationSafeArgs)
        classpath(Config.Dependencies.dagger)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri(Config.Repositories.gradleMaven) }
        maven { url = uri(Config.Repositories.jitpack) }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}