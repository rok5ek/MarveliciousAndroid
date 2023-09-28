object Versions {
    val androidCoreKtx = "1.12.0"
    val dagger = "2.45"
    val retrofit = "2.9.0"
    val retrofitKotlinSerializer = "1.0.0"
    val rxjava = "2.1.9"
}

object Dependencies {
    val androidCoreKtx = "androidx.core:core-ktx:${Versions.androidCoreKtx}"
    val daggerHilt = "com.google.dagger:hilt-android:${Versions.dagger}"
    val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitKotlinSerializer = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinSerializer}"
}