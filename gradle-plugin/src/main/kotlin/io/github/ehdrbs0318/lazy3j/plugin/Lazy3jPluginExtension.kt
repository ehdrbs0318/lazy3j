package io.github.ehdrbs0318.lazy3j.plugin

import org.gradle.api.internal.provider.DefaultProperty
import org.gradle.api.internal.provider.PropertyHost
import org.gradle.api.provider.Property

open class Lazy3jPluginExtension {
    var jsonDir: Property<String> = DefaultProperty(PropertyHost.NO_OP, String::class.java)
    var generatedDir: Property<String> = DefaultProperty(PropertyHost.NO_OP, String::class.java)
    var packageName: Property<String> = DefaultProperty(PropertyHost.NO_OP, String::class.java)
    var generateJavaWhenCompile: Boolean = false
}