import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * https://github.com/LuckSiege/PictureSelector
 */
class PickerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.kapt")
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("glide").get())
                add("kapt", libs.findLibrary("glide.compiler").get())
                add("implementation", libs.findLibrary("picker").get())
                add("implementation", libs.findLibrary("picker.compress").get())
                add("implementation", libs.findLibrary("picker.ucrop").get())
                add("implementation", libs.findLibrary("picker.camerax").get())
            }
        }
    }
}