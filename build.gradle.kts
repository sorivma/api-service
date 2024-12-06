plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.20"
    `maven-publish`
}

group = "com.sorivma"
version = "0.0.1-SNAPSHOT"

ext {
    set("springBootVersion", "3.3.3")
    set("postgresqlVersion", "42.7.2")
    set("dataFakerVersion", "1.5.0")
    set("graphqlVersion", "latest.release")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    maven {
        name = "nexus"
        url = uri("http://91.210.170.21:8085/repository/maven-snapshots")
        isAllowInsecureProtocol = true
        credentials {
            username = System.getenv("NEXUS_USERNAME")
            password = System.getenv("NEXUS_PASSWORD")
        }
    }
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:${property("graphqlVersion")}")
    }
}

dependencies {
    // Spring Boot and general dependencies
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Database dependencies
    implementation("org.postgresql:postgresql:${property("postgresqlVersion")}")

    // Faker for generating fake data
    implementation("net.datafaker:datafaker:${property("dataFakerVersion")}")

    // GraphQL dependencies
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-graphql-starter")

    implementation("com.sorivma:antifraud-api:0.1.3-SNAPSHOT")

    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.amqp:spring-rabbit-test")
    testImplementation("io.mockk:mockk:1.13.5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // H2 database for development
    developmentOnly("com.h2database:h2:2.1.214")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.sorivma"
            artifactId = "api-service"
            version = "${project.version}"
        }
    }
    repositories {
        maven {
            name = "nexus"
            url = uri("http://91.210.170.21:8085/repository/maven-snapshots")
            isAllowInsecureProtocol = true
            credentials {
                username = System.getenv("NEXUS_USERNAME")
                password = System.getenv("NEXUS_PASSWORD")
            }
        }
    }
}

