plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val baseUrl = "\"https://api.tvmaze.com/\""

        all {
            buildConfigField("String", "BASE_URL", baseUrl)
        }
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(NetworkDependencies.retrofit)
    implementation(NetworkDependencies.okhttp)
    implementation(NetworkDependencies.gson)
    implementation(NetworkDependencies.gson_converter)
    implementation(SupportDependencies.paging)

    implementation(DependencyInjectionDependencies.koin)
    implementation(DependencyInjectionDependencies.koin_core)

    implementation(DatabaseDependencies.room)
    implementation(DatabaseDependencies.room_ktx)
    kapt(DatabaseDependencies.room_compiler)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
}