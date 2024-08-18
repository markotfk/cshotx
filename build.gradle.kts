plugins {
    application
    id("xyz.jpenilla.run-paper") version "2.3.0"
}


tasks {
  runServer {
    // Configure the Minecraft version for our task.
    // This is the only required configuration besides applying the plugin.
    // Your plugin's jar (or shadowJar if present) will be used automatically.
    minecraftVersion("1.21.1")
  }
}

repositories {
    mavenCentral()
    maven {
        name = "Spigot"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        name = "EngineHub"
        url = uri("https://maven.enginehub.org/repo/")
    }
    maven {
        name = "PaperMC"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "sonatype-oss-snapshots"
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}