@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.android.library.utils"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    val composeBom = platform(libs.androidx.compose.bom)
    api(composeBom)
    api(libs.kotlin.stdlib)
    api(libs.kotlinx.coroutines.android)
    api(libs.googlemaps.maps)
    api(libs.googlemaps.compose)
    api(libs.androidx.appcompat)
    api(libs.androidx.activity.compose)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.materialWindow)
    api(libs.androidx.compose.animation)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.runtime.livedata)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.navigation.compose)
    api(libs.androidx.lifecycle.process)
    api(libs.androidx.lifecycle.viewModelCompose)
    api(libs.androidx.hilt.navigation.compose)
    api(libs.coil.kt.compose)
    api(libs.moshi)
    api(libs.zxing)
    api(libs.moshi.kotlin)
    api(libs.retrofit)
    api(libs.retrofit.converter)
    coreLibraryDesugaring(libs.core.jdk.desugaring)
}