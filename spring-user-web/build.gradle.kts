plugins {
	java
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
	id ("org.liquibase.gradle") version "2.2.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"


java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(24)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	//implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.data:spring-data-jpa:3.5.2")
	implementation("org.liquibase:liquibase-core")
	implementation("org.postgresql:postgresql:42.7.7")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-mail:3.1.5")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")

	implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.3.0")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.3.0")
	implementation(platform("org.springframework.cloud:spring-cloud-dependencies:Edgware.SR4"))

	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:3.3.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator:3.5.4")

	implementation("org.springframework.cloud:spring-cloud-starter-config:4.3.0")
	implementation("org.springframework.cloud:spring-cloud-config-client:4.3.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
