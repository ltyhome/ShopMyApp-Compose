buildscript {
    repositories {
        google()
        mavenCentral()

        if (!libs.versions.compose.snapshot.get().endsWith("SNAPSHOT")) {
            maven {
                url =
                    uri("https://androidx.dev/snapshots/builds/${libs.versions.compose.snapshot.get()}/artifacts/repository/")
            }
        }
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.hilt.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.secrets.gradlePlugin)
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.github.ben-manes.versions") version "0.46.0"
    id("nl.littlerobots.version-catalog-update") version "0.7.0"
    id("com.android.application") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("com.android.library") version "7.3.1" apply false
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    id("com.ncorti.ktfmt.gradle") version "0.12.0"
}

apply("${project.rootDir}/buildscripts/toml-updater-config.gradle")

detekt {
    buildUponDefaultConfig = true // preconfigure defaults
    allRules = false // activate all available (even unstable) rules.
    config = files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true) // observe findings in your browser with structure and code snippets
        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
        sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with Github Code Scanning
        md.required.set(true) // simple Markdown format
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}

ktfmt {
    kotlinLangStyle()
}