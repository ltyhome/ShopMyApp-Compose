import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class GoogleConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                val bom = libs.findLibrary("firebase.bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findLibrary("analytics.ktx").get())
                add("implementation", libs.findLibrary("crashlytics.ktx").get())
                add("implementation", libs.findLibrary("crashlytics.ndk").get())
                add("implementation", libs.findLibrary("google.location").get())
                add("implementation", libs.findLibrary("google.map").get())
                add("implementation", libs.findLibrary("google.map.ktx").get())
                add("implementation", libs.findLibrary("google.pay").get())
            }
        }
    }
}