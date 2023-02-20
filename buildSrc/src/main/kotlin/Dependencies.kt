object AndroidDependencies {
    val gradle by lazy { "com.android.tools.build:gradle:${AndroidVersions.gradle}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${AndroidVersions.kotlin}" }
    val dependencyUpdate by lazy { "com.github.ben-manes:gradle-versions-plugin:${AndroidVersions.dependency_updates}" }
    val detekt by lazy { "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${AndroidVersions.detekt}" }
    val navigationSafeArgs by lazy {
        "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidVersions.navigation_safe_args}"
    }
}

object SupportDependencies {
    val core by lazy { "androidx.core:core-ktx:${SupportVersions.core_version}" }
    val material by lazy { "com.google.android.material:material:${SupportVersions.material_version}" }
    val app_compat by lazy { "androidx.appcompat:appcompat:${SupportVersions.app_compat_version}" }
    val constraint by lazy { "androidx.constraintlayout:constraintlayout:${SupportVersions.constraint_version}" }
    val lifecycle by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${SupportVersions.lifecycle_version}" }
    val live_data by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${SupportVersions.lifecycle_version}" }
    val navigation by lazy { "androidx.navigation:navigation-ui-ktx:${SupportVersions.navigation_version}" }
    val navigation_fragment by lazy { "androidx.navigation:navigation-fragment-ktx:${SupportVersions.navigation_version}" }
    val coil by lazy { "io.coil-kt:coil:${SupportVersions.coil_version}" }
    val shimmer by lazy { "com.facebook.shimmer:shimmer:${SupportVersions.shimmer_version}" }
    val splash by lazy { "androidx.core:core-splashscreen:${SupportVersions.splash_version}" }
    val swipe_refresh by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:${SupportVersions.swipe_refresh_layout_version}" }
    val flexbox by lazy { "com.google.android.flexbox:flexbox:${SupportVersions.flexbox_version}" }
    val paging by lazy { "androidx.paging:paging-runtime:${SupportVersions.paging_version}" }
    val paging_common by lazy { "androidx.paging:paging-common:${SupportVersions.paging_version}" }
}

object NetworkDependencies {
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${NetworkVersions.retrofit_version}" }
    val okhttp by lazy { "com.squareup.okhttp3:logging-interceptor:${NetworkVersions.okHttp_interceptor_version}" }
    val gson by lazy { "com.google.code.gson:gson:${NetworkVersions.gson_version}" }
    val gson_converter by lazy { "com.squareup.retrofit2:converter-gson:${NetworkVersions.gson_converter_version}" }
}

object DependencyInjectionDependencies {
    val koin by lazy { "io.insert-koin:koin-android:${DependencyInjectionVersions.koin_version}" }
    val koin_core by lazy { "io.insert-koin:koin-core:${DependencyInjectionVersions.koin_version}" }
}

object DatabaseDependencies {
    val room by lazy { "androidx.room:room-runtime:${DatabaseVersions.room_version}" }
    val room_ktx by lazy { "androidx.room:room-ktx:${DatabaseVersions.room_version}" }
    val room_compiler by lazy { "androidx.room:room-compiler:${DatabaseVersions.room_version}" }
}

object AsyncDependencies {
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${AsyncVersions.coroutines_version}" }
    val coroutines_android by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${AsyncVersions.coroutines_version}" }
}

object TestDependencies {
    val arch by lazy { "androidx.arch.core:core-testing:${TestVersions.arch}" }
    val junit by lazy { "junit:junit:${TestVersions.junit}" }
    val junit_instrumentation by lazy { "androidx.test.ext:junit:${TestVersions.junit_instrumentation}" }
    val mockk by lazy { "io.mockk:mockk:${TestVersions.mockk}" }
    val mockk_android by lazy { "io.mockk:mockk-android:${TestVersions.mockk}" }
    val coroutines_test by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestVersions.coroutines_test}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${TestVersions.espresso}" }
    val test_core by lazy { "androidx.test:core:${TestVersions.test_core}" }
    val kotlin_test by lazy { "org.jetbrains.kotlin:kotlin-test:${AndroidVersions.kotlin}" }
    val fragment_testing by lazy { "androidx.fragment:fragment-testing:${TestVersions.fragment_version}" }
}