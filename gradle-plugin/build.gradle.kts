plugins {
    id("java-gradle-plugin")
    id("maven-publish")
    id("com.gradle.plugin-publish") version "0.16.0"
}

dependencies {
    api("org.web3j:codegen:${libs.versions.web3j.get()}")
    implementation(gradleApi())
}

gradlePlugin {
    plugins {
        create("lazy3jPlugin") {
            id = "io.github.ehdrbs0318.lazy3j"
            displayName = "lazy3jPlugin"
            description = "create java file from smart contract abi(json) file."
            implementationClass = "io.github.ehdrbs0318.lazy3j.plugin.Lazy3jPlugin"
            uri("$buildDir/repo")
        }
    }
}

pluginBundle {
    website = "https://github.com/ehdrbs0318/lazy3j"
    vcsUrl = "https://github.com/ehdrbs0318/lazy3j"
    tags = listOf("smartContract", "generate", "web3j", "lazy3j")
}

publishing {
    repositories {
        maven {
            setUrl("$buildDir/repo")
        }
    }
}