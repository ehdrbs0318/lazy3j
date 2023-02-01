# lazy3j

## What it is

lazy3j is simple plugin to create java file from smart contract abi(json) file.

## how it works

this plugin uses [web3j](https://github.com/web3j/web3j)(more specifically org.web3j:codegen)

it reads Ethereum [abi](https://docs.soliditylang.org/en/latest/abi-spec.html#json) file in specific directory and
generate all java files and
include it to sourceSets

## How to use it

### installation

build.gradle.kts

```kotlin
plugins {
    id("io.github.ehdrbs0318.lazy3j") version "${lazy3jVersion}"
}

dependencies {
    implementation("org.web3j:core:4.9.6")
}
```

### configuration

build.gradle.kts

```kotlin
lazy3j {
    packageName.set("your.package.name.contract")
    generateJavaWhenCompile = true
}
```

#### extension properties

| name                    | description                                                                                                               | type    | default value                                |
|-------------------------|---------------------------------------------------------------------------------------------------------------------------|---------|----------------------------------------------|
| jsonDir                 | abi file directory                                                                                                        | String  | ${projectDir.absolutePath}                   |
| generatedDir            | generated java file directory                                                                                             | String  | ${buildDir.absolutePath}/generated/contracts |
| packageName             | java files package name                                                                                                   | String  | io.github.ehdrbs0318.lazy3j.contracts        |
| generateJavaWhenCompile | execute `generateJavaFromJson` task before `compileKotlin` task if task exists, or execute task before `compileJava` task | Boolean | false                                        |

### run

```bash
./gradlew generateJavaFromJson
```