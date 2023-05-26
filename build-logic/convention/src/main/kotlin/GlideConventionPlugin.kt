import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class GlideConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("glide").get())
                add("implementation", libs.findLibrary("glide.transformations").get())
                add("implementation", libs.findLibrary("glide.compose").get())
                add("implementation", libs.findLibrary("glide.okhttp").get())
            }
        }
    }
}