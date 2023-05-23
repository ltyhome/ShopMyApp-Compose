import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `maven-publish`
}

group = "com.ltyhome.plugin"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("Application") {
            id = "ltyhome.android.application"
            implementationClass = "ApplicationConventionPlugin"
        }
        register("Library") {
            id = "ltyhome.android.library"
            implementationClass = "LibraryConventionPlugin"
        }
        register("AppCompose") {
            id = "ltyhome.android.app.compose"
            implementationClass = "AppComposeConventionPlugin"
        }
        register("LibCompose") {
            id = "ltyhome.android.lib.compose"
            implementationClass = "LibComposeConventionPlugin"
        }
        register("Retrofit") {
            id = "ltyhome.android.retrofit"
            implementationClass = "RetrofitConventionPlugin"
        }
        register("Ktor") {
            id = "ltyhome.android.ktor"
            implementationClass = "KtorConventionPlugin"
        }
        register("Glide") {
            id = "ltyhome.android.glide"
            implementationClass = "GlideConventionPlugin"
        }
        register("Coil") {
            id = "ltyhome.android.coil"
            implementationClass = "CoilConventionPlugin"
        }
        register("Hilt") {
            id = "ltyhome.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("Room") {
            id = "ltyhome.android.room"
            implementationClass = "RoomConventionPlugin"
        }
        register("Picker") {
            id = "ltyhome.android.picker"
            implementationClass = "PickerConventionPlugin"
        }
    }
}
