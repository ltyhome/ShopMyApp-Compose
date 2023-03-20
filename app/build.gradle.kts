@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt")
    id("com.ncorti.ktfmt.gradle")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.android.compose.dev"
    defaultConfig {
        applicationId = "com.android.compose"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["dagger.hilt.disableModulesHaveInstallInCheck"] = "true"
            }
        }
    }

    signingConfigs {
        named("debug") {
            storeFile = rootProject.file("debug.jks")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }

        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
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

    packagingOptions {
        resources.excludes.addAll(mutableSetOf("/META-INF/AL2.0","/META-INF/LGPL2.1"))
    }
}

kapt {
    javacOptions {
        option("-Xmaxerrs",500)
    }
}

dependencies {
    implementation(libs.hilt.android)
    implementation(libs.androidx.startup)
    kapt(libs.hilt.compiler)
    kapt(libs.hilt.ext.compiler)
    api(project(":library"))
    coreLibraryDesugaring(libs.core.jdk.desugaring)
}

secrets {
    defaultPropertiesFileName = "local.defaults.properties"
}
