plugins {
//    alias(libs.plugins.android.library)
//    alias(libs.plugins.jetbrains.kotlin.android)
//
//
//    alias(libs.plugins.hilt)
//    id("kotlin-kapt")
//
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "uz.gita.common"
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
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//
    //VOYAGER
    implementation(libs.voyager.navigator)// NAVIGATOR
    implementation(libs.voyager.screenModel)// SCREEN MODEL
    implementation(libs.voyager.transitions) //TRANSITION
    implementation(libs.voyager.hilt) //HILT
//
//    //HILT DI
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)
//
//
//    debugImplementation ("androidx.compose.ui:ui-tooling:1.7.4")
//    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.7.4")
//
    //GSON
    implementation(libs.converter.gson)
//
//
//    compileOnly ("androidx.compose.ui:ui:1.7.4")
//    compileOnly ("androidx.compose.ui:ui-tooling-preview:1.7.4")
//    compileOnly ("androidx.compose.material3:material3:1.3.0")
//
//    //Orbit
//    compileOnly("org.orbit-mvi:orbit-viewmodel:4.3.2")
//    compileOnly("org.orbit-mvi:orbit-compose:4.3.2")
//

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //VOYAGER
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.hilt)
    implementation(libs.voyager.transitions)


}