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
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.apache.commons:commons-text:1.9")
    implementation(kotlin("stdlib"))
}
