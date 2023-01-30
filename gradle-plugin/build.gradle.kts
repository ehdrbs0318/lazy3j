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
        create("lazy3jPlugin") {
            id = "io.github.ehdrbs0318.lazy3j"
            displayName = "lazy3jPlugin"
            description = "create java file from smart contract abi(json) file."
            implementationClass = "io.github.ehdrbs0318.lazy3j.plugin.Lazy3jPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/ehdrbs0318/lazy3j"
    vcsUrl = "https://github.com/ehdrbs0318/lazy3j"
    tags = listOf("smartContract", "generate", "web3j")
}