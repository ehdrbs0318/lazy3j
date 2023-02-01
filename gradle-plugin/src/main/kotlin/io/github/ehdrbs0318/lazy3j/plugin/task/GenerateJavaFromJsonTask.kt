package io.github.ehdrbs0318.lazy3j.plugin.task

import io.github.ehdrbs0318.lazy3j.plugin.Lazy3jPluginExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.web3j.codegen.TruffleJsonFunctionWrapperGenerator
import java.io.File
import java.nio.file.Paths

/**
 * 자바코드 생성 task
 */
open class GenerateJavaFromJsonTask : DefaultTask() {
    companion object {
        const val NAME = "generateJavaFromJson"
    }


    @TaskAction
    fun generate() {
        val extension = project.extensions.getByType(Lazy3jPluginExtension::class.java)
        val jsonDir = File(extension.jsonDir.get())
        // jsonDir 경로에 있는 .json 파일들
        val jsonFiles = jsonDir
                .list { _, name -> name.endsWith(".json", true) }
                ?.toList()
                ?.map { Paths.get(extension.jsonDir.get(), it).toString() }
                ?: emptyList()
        project.logger.info("generating files from:\n${jsonFiles.joinToString("\n")}")
        for (jsonFile in jsonFiles) {
            try {
                TruffleJsonFunctionWrapperGenerator(
                        jsonFile,
                        extension.generatedDir.get(),
                        extension.packageName.get(),
                        true
                ).generate()
            } catch (e: Exception) {
                project.logger.error(e.message)
            }
        }
    }
}