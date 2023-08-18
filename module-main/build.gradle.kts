plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":module-web")))
    implementation(project(mapOf("path" to ":module-web")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}