object Versions {
    val androidCoreKtx = "1.12.0"
    val androidAppCompat = "1.6.1"
    val androidMaterial = "1.9.0"

    val composeNavigation = "2.7.3"
    val hiltNavigation = "1.0.0"
    val androidRuntime = "2.6.2"
    val coil = "2.2.2"

    val composeActivity = "1.8.0"
    val compose = "1.5.3"
    val composeMaterial = "1.1.2"

    val jUnit = "4.13.2"
    val jUnitAndroid = "1.1.5"
    val espresso = "3.5.1"
    val dagger = "2.45"
    val retrofit = "2.9.0"
    val retrofitKotlinSerializer = "1.0.0"
    val kotlinSerializer = "1.6.0"

    // retrofit http client
    val okhttp = "4.11.0"
}

object Dependencies {
    val androidCoreKtx = "androidx.core:core-ktx:${Versions.androidCoreKtx}"
    val androidAppCompat = "androidx.appcompat:appcompat:${Versions.androidAppCompat}"
    val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    val jUnit = "junit:junit:${Versions.jUnit}"
    val jUnitAndroid = "androidx.test.ext:junit:${Versions.jUnitAndroid}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    val ktxRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidRuntime}"
    val composeRuntime = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.androidRuntime}"
    val composeCoil = "io.coil-kt:coil-compose:${Versions.coil}"

    val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    val composeGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    val composePreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    val composeMaterial = "androidx.compose.material3:material3:${Versions.composeMaterial}"

    val daggerHilt = "com.google.dagger:hilt-android:${Versions.dagger}"
    val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitKotlinSerializer =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinSerializer}"
    val kotlinSerializer =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializer}"

    val okhttpMockServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val okhttpMockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}