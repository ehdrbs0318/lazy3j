plugins {
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.16.0"
}

dependencies {
    implementation(project(":core"))
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        create("com.github.ehdrbs0318.lazy3j") {
            id = "com.github.ehdrbs0318.lazy3j"
            implementationClass = "com.github.ehdrbs0318.lazy3j.plugin.Lazy3jPlugin"
        }
    }
}