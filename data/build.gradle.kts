plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.hilt)
    id("kotlin-kapt")

}

android {
    namespace = "uz.gita.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":common"))

    //RETROFITS
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //CHUCKER
    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") //LOGGING INTERCEPTOR

    //HILT DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}