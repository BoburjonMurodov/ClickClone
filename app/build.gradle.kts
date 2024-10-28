plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // Existing plugins
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "uz.gita.boboor.bankingappcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "uz.gita.boboor.bankingappcompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.transition)
    implementation(libs.androidx.espresso.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //DATE PICKER
    implementation(libs.core)
    implementation(libs.calendar)

    //RETROFITS
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //CHUCKER
    debugImplementation(libs.library)
    releaseImplementation(libs.library.no.op)

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
    implementation(libs.orbit.compose)


//SYSTEM UI CONTROLLER
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    implementation(project(":usecase"))
    implementation(project(":presenter"))
    implementation(project(":common"))
}

