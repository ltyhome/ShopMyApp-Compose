plugins {
    id("ltyhome.android.application")
    id("ltyhome.android.hilt")
    id("ltyhome.android.universal")
}

android {
    namespace = "com.ltyhome.template"
    defaultConfig {
        applicationId = "com.ltyhome.template"
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
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
    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
}