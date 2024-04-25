import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.kotlin.jpa)
    alias(libs.plugins.spotless)
}

group = "com.nagpal.shivam"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

/**
 * To allow Hibernate to create inherited proxy classes for lazy fetching
 * The support is provided by the plugin.spring and plugin.jpa
 *
 * Note: Entity classes should have an empty constructor as well
 */
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    implementation(libs.spring.boot.actuator)
    implementation(libs.spring.boot.data.jpa)
    implementation(libs.spring.boot.web)
    implementation(libs.spring.boot.validation)
    implementation(libs.spring.boot.security)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.flyway.core)
    implementation(libs.auth0.jwt)
    implementation(libs.kotlin.reflect)
    implementation(libs.logback.logstash.encoder)

    developmentOnly(libs.spring.boot.devtools)

    runtimeOnly(libs.postgresql)

    testImplementation(libs.spring.boot.test)
}

spotless {
    kotlin {
        ktlint()
        endWithNewline()
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
