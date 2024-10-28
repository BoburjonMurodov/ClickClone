plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.hilt)
    id("kotlin-kapt")

}

android {
    namespace = "uz.gita.presenter"
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
    implementation(project(":usecase"))


    //HILT DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)


    //VOYAGER
    implementation(libs.voyager.navigator)// NAVIGATOR
    implementation(libs.voyager.screenModel)// SCREEN MODEL
    implementation(libs.voyager.transitions) //TRANSITION
    implementation(libs.voyager.hilt) //


    //ORBIT MVI
    implementation(libs.orbit.viewmodel)
    compileOnly("org.orbit-mvi:orbit-compose:4.3.2")
}