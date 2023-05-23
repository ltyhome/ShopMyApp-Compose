plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinParcelize) apply false
    alias(libs.plugins.googleServices) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.navigation) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.versions)
    alias(libs.plugins.update)
}
apply("${project.rootDir}/gradle/toml-updater-config.gradle")

//detekt {
//    buildUponDefaultConfig = true // preconfigure defaults
//    allRules = false // activate all available (even unstable) rules.
//    config = files("$projectDir/config/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
//    baseline = file("$projectDir/config/baseline.xml") // a way of suppressing issues before introducing detekt
//}
//
//tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
//    reports {
//        html.required.set(true) // observe findings in your browser with structure and code snippets
//        xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins
//        txt.required.set(true) // similar to the console output, contains issue signature to manually edit baseline files
//        sarif.required.set(true) // standardized SARIF format (https://sarifweb.azurewebsites.net/) to support integrations with Github Code Scanning
//        md.required.set(true) // simple Markdown format
//    }
//}
//
//tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
//    jvmTarget = "17"
//}
//tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
//    jvmTarget = "17"
//}
//
//ktfmt {
//    kotlinLangStyle()
//}