plugins {
    id("ltyhome.android.application")
    id("ltyhome.android.hilt")
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
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
}