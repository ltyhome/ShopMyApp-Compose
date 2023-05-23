import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class RetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("okhttp").get())
                add("implementation", libs.findLibrary("okhttp.logging").get())
                add("implementation", libs.findLibrary("okhttp.urlconnection").get())
                add("implementation", libs.findLibrary("retrofit").get())
                /**
                 * default converter moshi
                 * [gson converter.gson]
                 * [jackson converter.jackson]
                 */
                add("implementation", libs.findLibrary("moshi").get())
                add("implementation", libs.findLibrary("moshi.kotlin").get())
                add("implementation", libs.findLibrary("converter.moshi").get())
            }
        }
    }
}