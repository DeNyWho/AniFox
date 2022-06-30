plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinAndroidExtensions)
    id(Config.Plugins.kotlinter)
    id(Config.Plugins.kapt)
    id(Config.Plugins.navigationSafeArgs)
    id(Config.Plugins.serialization)
    id(Config.Plugins.dagger)
    id("org.jetbrains.kotlin.android")
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


        buildConfigField("String", "BASE_URL", "\"https://shikimori.one/api/\"")
        buildConfigField("String", "APP_LINK", "\"https://shikimori.one/oauth/authorize\"")
        buildConfigField("String", "AUTH_CODE", "\"authorization_code\"")
        buildConfigField("String", "REDIRECT_URI", "\"urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob\"")
        buildConfigField("String", "RESPONSE_TYPE", "\"code&scope=user_rates+comments+topics\"")
        buildConfigField("String", "CLIENT_ID", "\"sGl1uO_SjvpI5sDgKynbVFc8NrRd2e-_BPa_nj75DTk\"")
        buildConfigField("String", "CLIENT_SECRET", "\"6hvA2F3_Aw3sOnafBLrL9HIpVmUTS4_5Xgz0dXySPVk\"")
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

    implementation(Libraries.Glide.glide)
    kapt(Libraries.Glide.glideCompiler)

    implementation(Libraries.Hilt.hiltAndroid)
    kapt(Libraries.Hilt.hiltCompiler)

    implementation(Libraries.Retrofit.Retrofit)
    implementation(Libraries.Retrofit.RetrofitConverter)
    implementation(Libraries.Retrofit.Gson)

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