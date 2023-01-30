package io.github.ehdrbs0318.lazy3j.plugin

import io.github.ehdrbs0318.lazy3j.plugin.task.GenerateJavaFromJsonTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.UnknownTaskException
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.TaskProvider

class Lazy3jPlugin : Plugin<Project> {
    @Suppress()
    override fun apply(project: Project) {
        createExtension(project)
        registerGenerateJavaFromJsonTask(project)
        project.afterEvaluate {
            linkToCompileTask(project)
            addGeneratedClassesToSourceSets(project)
        }
    }

    private fun createExtension(project: Project) {
        project.extensions.create("lazy3j", Lazy3jPluginExtension::class.java).apply {
            val projectDirStr = project.projectDir.absolutePath

            // json directory path
            this.jsonDir.convention("$projectDirStr/abi")

            // generated java file path
            this.generatedDir.convention("${project.buildDir.absolutePath}/generated/contracts")

            // package names
            this.packageName.convention("io.github.ehdrbs0318.lazy3j.contracts")

            // run generate on compileTask
            this.generateJavaWhenCompile.convention(false)
        }
    }

    private fun registerGenerateJavaFromJsonTask(project: Project): TaskProvider<GenerateJavaFromJsonTask> {
        return project.tasks.register(GenerateJavaFromJsonTask.NAME, GenerateJavaFromJsonTask::class.java)
    }

    // 컴파일 task 전에 generateJavaFromJsonTask 실행
    private fun linkToCompileTask(project: Project) {
        val autoGenerate = project.extensions
                .getByType(Lazy3jPluginExtension::class.java)
                .generateJavaWhenCompile.get()
        if (autoGenerate) {
            val task = try {
                project.tasks.getByName("compileKotlin")
            } catch (e: UnknownTaskException) {
                project.tasks.getByName("compileJava")
            }
            task.dependsOn(GenerateJavaFromJsonTask.NAME)
        }
    }

    /**
     * 생성된 코드를 소스 세트에 포함
     */
    private fun addGeneratedClassesToSourceSets(project: Project) {
        project.extensions.getByType(JavaPluginExtension::class.java).sourceSets.named("main") {
            it.java.srcDir("${project.buildDir}/generated/contracts")
        }
    }
}