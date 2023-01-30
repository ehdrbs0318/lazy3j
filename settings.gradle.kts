rootProject.name = "lazy3j"

include("core")
include("gradle-plugin")

// 플러그인 버전 관리
pluginManagement {
    repositories {
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    plugins {
        kotlin("jvm") version "1.7.22"
    }
}

// 모듈 의존성 버전 관리
dependencyResolutionManagement {
    versionCatalogs {
        // 라이브러리 버전
        create("libs") {
            version("web3j", "4.9.6")
        }
    }
}