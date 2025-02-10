
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    //코틀린 kapt 사용을 위해 선언
    id("kotlin-kapt")
    //hilt 플러그인 사용을 위해 선언
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.leaveit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.leaveit"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        var properties = Properties()
        properties.load(FileInputStream("local.properties"))

        buildConfigField("String", "TOUR_API_KEY", properties.getProperty("TOUR_API_KEY"))
        buildConfigField("String","TEST_TOKEN",properties.getProperty("TEST_TOKEN"))
        buildConfigField("String","MAP_API_KEY_ID",properties.getProperty("MAP_API_KEY_ID"))
        buildConfigField("String","MAP_API_KEY",properties.getProperty("MAP_API_KEY"))
        manifestPlaceholders["MAP_API_KEY"] = properties["MAP_API_KEY_ID_MANIFEST"] as Any


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
    viewBinding {
        enable = true
    }
    dataBinding{
        enable = true
    }
    kapt{
        correctErrorTypes =  true
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    val room_version = "2.6.1"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("androidx.fragment:fragment-ktx:1.8.5")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    //koroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    //circleImageView
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    //ViewPager2 & indicator
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("com.tbuonomo:dotsindicator:5.0")

    // 네이버 지도 SDK
    implementation("com.naver.maps:map-sdk:3.20.0")

    // Paging 3
    implementation("androidx.paging:paging-runtime:3.3.4")


    // 레이팅바 라이브러리
    implementation ("com.github.ome450901:SimpleRatingBar:1.5.1")

    // 스플래시 화면 라이브러리
    implementation("androidx.core:core-splashscreen:1.0.0")

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //GogleLocation
    implementation("com.google.android.gms:play-services:12.0.1")

}