import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class UniversalConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("adapter").get())
                add("implementation", libs.findLibrary("flyco").get())
                add("implementation", libs.findLibrary("smartrefresh.layout").get())
                add("implementation", libs.findLibrary("smartrefresh.header").get())
                add("implementation", libs.findLibrary("smartrefresh.footer").get())
                add("implementation", libs.findLibrary("timber").get())
                add("implementation", libs.findLibrary("zbar").get())
                add("implementation", libs.findLibrary("intro").get())
                add("implementation", libs.findLibrary("image.preview").get())
                add("implementation", libs.findLibrary("immersionbar").get())
                add("implementation", libs.findLibrary("immersionbar.comp").get())
                add("implementation", libs.findLibrary("immersionbar.ktx").get())
                add("implementation", libs.findLibrary("agent.web").get())
            }
        }
    }
}