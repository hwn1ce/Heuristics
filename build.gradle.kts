plugins {
    java
}

group = "me.shsmith0206"

version = "0.1.0"

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {

}

dependencies {

}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "me.shsmith0206.heuristics.Heuristics"
    }
}