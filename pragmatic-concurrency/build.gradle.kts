import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

val kotlin = "1.3.61"
val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${kotlin}"
val kotlin_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlin}"
val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${kotlin}"

plugins {
    kotlin("jvm") version "1.3.61" apply true
}

dependencies{
    api(kotlin_stdlib)
    api(kotlin_jdk8)
    api(kotlin_reflect)

    api("org.elasticsearch.client:transport:6.6.0")
    api("org.elasticsearch:elasticsearch:6.6.0")
    api("org.apache.kafka:kafka_2.12:1.1.1")
    api("org.apache.kafka:kafka_2.12:1.1.1")
    api("ru.fix:dynamic-property-api:1.1.9")
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    withType<Test> {
        useJUnitPlatform()

        maxParallelForks = 10

        testLogging {
            events(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
            showStandardStreams = true
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}


