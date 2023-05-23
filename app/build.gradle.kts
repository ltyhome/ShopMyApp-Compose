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