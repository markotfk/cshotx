import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.the


plugins {
    `java-library`
}
project.description = "Plugin"

applyCommonConfiguration()

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

repositories {
    mavenCentral()
    maven {
        name = "PaperMC"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "EngineHub"
        url = uri("https://maven.enginehub.org/repo/")
    }
    maven {
        name = "OSS Sonatype Snapshots"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    maven {
            name = "Athion"
            url = uri("https://ci.athion.net/plugin/repository/tools/")
        }
    flatDir { dir(File("src/main/resources")) }
}

fun Project.applyCommonConfiguration() {
    group = rootProject.group
    version = rootProject.version

    configurations.all {
        resolutionStrategy {
            cacheChangingModulesFor(5, "MINUTES")
        }
    }

    plugins.withId("java") {
        the<JavaPluginExtension>().toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    dependencies {
        implementation("io.papermc.paper:dev-bundle:1.21.1-R0.1-20240817.214809-30")
        implementation("net.kyori:adventure-api:4.17.0")
        constraints {
            for (conf in configurations) {
                if (conf.isCanBeConsumed || conf.isCanBeResolved) {
                    // dependencies don't get declared in these
                    continue
                }
                add(conf.name, "com.google.guava:guava") {
                    version { require("31.1-jre") }
                    because("Mojang provides Guava")
                }
                add(conf.name, "com.google.code.gson:gson") {
                    version { require("2.10") }
                    because("Mojang provides Gson")
                }
                add(conf.name, "it.unimi.dsi:fastutil") {
                    version { require("8.5.9") }
                    because("Mojang provides FastUtil")
                }
            }
        }
    }
}
