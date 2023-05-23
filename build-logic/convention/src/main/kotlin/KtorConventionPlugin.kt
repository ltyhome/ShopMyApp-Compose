import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * https://old.arrow-kt.io/docs/core/
 * https://old.arrow-kt.io/docs/apidocs/arrow-core/arrow.core/-either/
 * https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html
 */
class KtorConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                val bom = libs.findLibrary("arrow.bom").get()
                add("implementation", platform(bom))
                add("implementation", libs.findLibrary("gson").get())
                add("implementation", libs.findLibrary("arrow.core").get())
                add("implementation", libs.findLibrary("arrow.fx.coroutines").get())
                add("implementation", libs.findLibrary("ktor.core").get())
                add("implementation", libs.findLibrary("ktor.okhttp").get())
                add("implementation", libs.findLibrary("ktor.logging").get())
                add("implementation", libs.findLibrary("ktor.negotiation").get())
                add("implementation", libs.findLibrary("ktor.serialization").get())
            }
        }
    }
}