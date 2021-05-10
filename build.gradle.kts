plugins {
    java
    id("com.github.johnrengelman.shadow").version("6.1.0")
}

group = "me.shsmith0206"

version = "0.1.0"

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    maven { url = uri("https://repo.codemc.org/repository/maven-public") }
    mavenCentral()
}

dependencies {
    implementation("com.dfsek:Tectonic:1.3.1")
    implementation("org.yaml:snakeyaml:1.27")
    implementation("commons-io:commons-io:2.8.0")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "me.shsmith0206.heuristics.Heuristics"
    }
}