plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.zali.aparat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zali.aparat"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //life cycle for view model
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    //retrofit for call rest api
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //exo player for play video
    val exo_version = "2.19.1"
    implementation("com.google.android.exoplayer:exoplayer-core:$exo_version")
    implementation("com.google.android.exoplayer:exoplayer-dash:$exo_version")
    implementation("com.google.android.exoplayer:exoplayer-ui:$exo_version")
    implementation("com.google.android.exoplayer:exoplayer-hls:$exo_version")

    ///navigation graph for related each fragment
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")


    //rxjava3 for reactive programing
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("io.reactivex.rxjava3:rxjava:3.0.2")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

    implementation("com.mindorks.android:prdownloader:0.6.0")

    implementation("com.github.bumptech.glide:glide:4.16.0")

    implementation("de.hdodenhof:circleimageview:3.1.0")

    implementation("com.github.GrenderG:Toasty:1.5.2")

    implementation("com.airbnb.android:lottie:5.2.0")


}