plugins {
    kotlin("jvm")
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "io.github.ehdrbs0318.lazy3j"
    version = "1.0.0"
    java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(kotlin("test"))
    }
}
tasks.test {
    useJUnitPlatform()
}