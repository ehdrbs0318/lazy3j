package io.github.ehdrbs0318.lazy3j.plugin

import org.gradle.api.internal.provider.DefaultProperty
import org.gradle.api.internal.provider.PropertyHost
import org.gradle.api.provider.Property

open class Lazy3jPluginExtension {
    val jsonDir: Property<String> = DefaultProperty(PropertyHost.NO_OP, String::class.java)
    val generatedDir: Property<String> = DefaultProperty(PropertyHost.NO_OP, String::class.java)
    val packageName: Property<String> = DefaultProperty(PropertyHost.NO_OP, String::class.java)
}