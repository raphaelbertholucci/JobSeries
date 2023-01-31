plugins {
    id("kotlin")
    id("kotlin-kapt")
}

dependencies {
    implementation(AsyncDependencies.coroutines)
    implementation(AsyncDependencies.coroutines_android)

    implementation(DependencyInjectionDependencies.koin_core)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.arch)
}