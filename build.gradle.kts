plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring BOM
    implementation(enforcedPlatform("org.springframework:spring-framework-bom:6.1.10"))

    // Spring модули
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-webmvc")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework.data:spring-data-jdbc:4.0.2")
    implementation("org.jspecify:jspecify:1.0.0")

    // База данных
    implementation("org.postgresql:postgresql:42.7.9")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.17.0")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.17.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    // Jakarta
    implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    // Jetty для локального запуска
    implementation("org.eclipse.jetty:jetty-server:11.0.15")
    implementation("org.eclipse.jetty:jetty-servlet:11.0.15")


    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    testImplementation("org.springframework:spring-test")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.11.2")


    testImplementation("org.mockito:mockito-junit-jupiter:5.21.0")
    testImplementation("org.mockito:mockito-core:5.21.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("com.h2database:h2:2.4.240")
    testImplementation("org.testcontainers:junit-jupiter:1.19.0")
    testImplementation("org.testcontainers:postgresql:1.19.0")
    implementation("com.jayway.jsonpath:json-path:2.10.0")

}

tasks.test {
    useJUnitPlatform()
}