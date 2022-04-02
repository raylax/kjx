plugins {
    kotlin("jvm") version "1.4.32"
}

group = "org.inurl.kjx"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:31.1-jre")
    implementation(kotlin("stdlib"))
}
