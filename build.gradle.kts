buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri(Config.Repositories.gradleMaven) }
    }
    dependencies {
        classpath(Config.Dependencies.androidPlugin)
        classpath(kotlin(Config.Dependencies.kotlinPlugin, Versions.kotlin))
        classpath(Config.Dependencies.kotlinter)
        classpath(Config.Dependencies.serialization)
        classpath(Config.Dependencies.navigationSafeArgs)
        classpath(Config.Dependencies.dagger)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url = uri(Config.Repositories.gradleMaven) }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}