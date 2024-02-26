plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.gms.google-services")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.appcinemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appcinemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")


    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:10.0.5")
    implementation ("com.github.HamidrezaAmz:MagicalExoPlayer:3.0.5")
    implementation ("com.airbnb.android:lottie:4.2.1")

    // retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //Dagger
    implementation ("com.google.dagger:hilt-android:2.41")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")
    implementation ("androidx.test.espresso:espresso-contrib:3.4.0")
    kapt ("com.google.dagger:hilt-android-compiler:2.40.1")
    //implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    kapt ("androidx.hilt:hilt-compiler:1.0.0")

}