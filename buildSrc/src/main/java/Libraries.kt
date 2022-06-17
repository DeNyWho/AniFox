object Libraries {
    const val kotlinStdlib =  "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    object Supports {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    }

    object Room {
        const val RoomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val RoomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val RoomCompiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object DataStore {
        const val DataStoreCore = "androidx.datastore:datastore-core:${Versions.dataStore}"
        const val DataStorePreferences = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
        const val DataStorePreferencesCore = "androidx.datastore:datastore-preferences-core:${Versions.dataStore}"
    }

    object Dagger {
        const val DaggerApi = "com.google.dagger:dagger:${Versions.dagger}"
        const val DaggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val DaggerAndroid = "com.google.dagger:dagger-android:${Versions.daggerAndroid}"
        const val DaggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.daggerAndroidSup}"
        const val DaggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerAndroidSup}"
    }

    object Serialization {
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serializationJson}"
    }

    object Logger {
        const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object Groupie {
        const val groupie = "com.github.lisawray.groupie:groupie:${Versions.groupie}"
        const val groupieViewBinding = "com.github.lisawray.groupie:groupie-viewbinding:${Versions.groupie}"
        const val groupieDataBinding = "com.github.lisawray.groupie:groupie-databinding:${Versions.groupie}"
    }

    object Paging {
        const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    }

    object Retrofit {
        const val Gson = "com.google.code.gson:gson:${Versions.retrofit}"
        const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val RetrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object Lifecycle {
        const val ViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val LiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val LifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
        const val LifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
        const val LiveDataCore = "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.lifecycle}"
    }

    object ViewPager {
        const val ViewPager = "androidx.viewpager2:viewpager2:${Versions.viewpager}"
    }

    object Navigation {
        const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val androidJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }
}