object Versions {
    val androidCoreKtx = "1.12.0"
    val dagger = "2.45"
    val retrofit = "2.9.0"
    val retrofitKotlinSerializer = "1.0.0"
    val kotlinSerializer = "1.6.0"

    // retrofit response modeling
    val sandwich = "1.3.9"

    // retrofit http client
    val okhttp = "4.11.0"
}

object Dependencies {
    val androidCoreKtx = "androidx.core:core-ktx:${Versions.androidCoreKtx}"
    val daggerHilt = "com.google.dagger:hilt-android:${Versions.dagger}"
    val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitKotlinSerializer =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinSerializer}"
    val kotlinSerializer =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializer}"
    val sandwich = "com.github.skydoves:sandwich:${Versions.sandwich}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val okhttpMockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}