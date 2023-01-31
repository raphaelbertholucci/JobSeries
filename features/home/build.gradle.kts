plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs")
    id("io.gitlab.arturbosch.detekt")
}

android {
    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(SupportDependencies.core)
    implementation(SupportDependencies.material)
    implementation(SupportDependencies.app_compat)
    implementation(SupportDependencies.coil)
    implementation(SupportDependencies.constraint)
    implementation(SupportDependencies.lifecycle)
    implementation(SupportDependencies.live_data)
    implementation(SupportDependencies.navigation)
    implementation(SupportDependencies.navigation_fragment)
    implementation(SupportDependencies.shimmer)
    implementation(SupportDependencies.splash)
    implementation(SupportDependencies.swipe_refresh)
    implementation(SupportDependencies.flexbox)

    implementation(DependencyInjectionDependencies.koin)
    implementation(DependencyInjectionDependencies.koin_core)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
}