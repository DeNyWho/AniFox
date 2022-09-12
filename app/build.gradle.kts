plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinAndroidExtensions)
    id(Config.Plugins.kotlinter)
    id(Config.Plugins.kapt)
    id(Config.Plugins.navigationSafeArgs)
    id(Config.Plugins.serialization)
    id(Config.Plugins.dagger)
}
android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = Application.id
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionName = Releases.versionName
        versionCode = Releases.versionCode
        testInstrumentationRunner = Config.testRunner
        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "BASE_URL_Main_API", "\"http://192.168.0.43:8080/api2/\"")
    }

    buildTypes {
        getByName("release") {
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
    dataBinding {
        isEnabled = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Libraries.UI.advancedCardView)
    implementation(Libraries.UI.tabSync)
    implementation(Libraries.UI.recyclerViewAnimation)
    implementation(Libraries.UI.showMoreTextview)

    implementation(Libraries.SwipeRefresh.swipeRefresh)

    implementation(Libraries.Groupie.groupie)
    implementation(Libraries.Groupie.groupieDataBinding)
    implementation(Libraries.Groupie.groupieViewBinding)

    implementation(Libraries.Paging.paging)

    implementation(Libraries.Logger.timber)
    implementation(Libraries.Logger.chucker)

    implementation(Libraries.Room.RoomKtx)
    implementation(Libraries.Room.RoomRuntime)
    kapt(Libraries.Room.RoomCompiler)

    implementation(Libraries.Lifecycle.LifecycleCommon)
    implementation(Libraries.Lifecycle.LifecycleExtensions)
    implementation(Libraries.Lifecycle.LiveData)
    implementation(Libraries.Lifecycle.LiveDataCore)
    implementation(Libraries.Lifecycle.ViewModel)
    implementation(Libraries.Lifecycle.LifeCycleRunTime)

    implementation(Libraries.DataStore.DataStoreCore)
    implementation(Libraries.DataStore.DataStorePreferences)
    implementation(Libraries.DataStore.DataStorePreferencesCore)

    implementation(Libraries.Picasso.picasso)

    implementation(Libraries.Glide.glide)
    implementation (Libraries.Glide.glideOkhttp)
    kapt(Libraries.Glide.glideCompiler)

    implementation(Libraries.Hilt.hiltAndroid)
    kapt(Libraries.Hilt.hiltCompiler)

    implementation(Libraries.Retrofit.Retrofit)
    implementation(Libraries.Retrofit.RetrofitConverter)
    implementation(Libraries.Retrofit.Gson)
    implementation(Libraries.Retrofit.okhttp)
    implementation(Libraries.Retrofit.okHttpLogging)
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    implementation(Libraries.Serialization.serialization)

    implementation(Libraries.ViewPager.ViewPager)

    implementation(Libraries.Dagger.DaggerApi)
    implementation(Libraries.Dagger.DaggerAndroid)
    kapt(Libraries.Dagger.DaggerCompiler)
    kapt(Libraries.Dagger.DaggerAndroidProcessor)
    implementation(Libraries.Dagger.DaggerAndroidSupport)

    implementation(Libraries.kotlinStdlib)
    implementation(Libraries.Supports.coreKtx)
    implementation(Libraries.Supports.appcompat)
    implementation(Libraries.Supports.material)
    implementation(Libraries.Supports.constraintlayout)

    implementation(Libraries.Navigation.fragmentKtx)
    implementation(Libraries.Navigation.uiKtx)
    testImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.androidJunit)
    androidTestImplementation(Libraries.Test.espressoCore)

}