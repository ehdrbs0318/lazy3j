package com.github.ehdrbs0318.lazy3j.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.web3j.codegen.TruffleJsonFunctionWrapperGenerator
import java.io.File
import java.nio.file.Paths

class Lazy3jPlugin : Plugin<Project> {
    @Suppress()
    override fun apply(project: Project) {
        val extension = project.extensions.create("lazy3j", Lazy3jPluginExtension::class.java)
        val projectDirStr = project.projectDir.absolutePath

        // json directory path
        extension.jsonDir.convention("$projectDirStr/abi")

        // generated java file path
        extension.generatedDir.convention("${project.buildDir.absolutePath}/generated/contracts")

        // package names
        extension.packageName.convention("com.github.ehdrbs0318.lazy3j.contracts")

        project.task("generateJavaFromJson").apply {
            group = "web3j"
            doLast {
                val jsonDir = File(extension.jsonDir.get())
                val jsonFiles = jsonDir
                        .list { _, name -> name.endsWith(".json", true) }
                        ?.toList()
                        ?.map { Paths.get(extension.jsonDir.get(), it).toString() }
                        ?: emptyList()
                println("files = $jsonFiles")
                for (jsonFile in jsonFiles) {
                    try {
                        TruffleJsonFunctionWrapperGenerator(
                                jsonFile,
                                extension.generatedDir.get(),
                                extension.packageName.get(),
                                true
                        ).generate()
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }
        }
    }
}