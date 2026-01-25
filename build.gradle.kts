plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework:spring-framework-bom:6.1.10"))
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-webmvc")
    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework.data:spring-data-jdbc:4.0.2")
    implementation("org.springframework:spring-test")
    implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
    implementation("org.postgresql:postgresql:42.7.9")

    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}