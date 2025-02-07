import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    kotlin("plugin.serialization") version "1.9.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {

    implementation(compose.desktop.currentOs)
    implementation("io.ktor:ktor-client-core:3.0.3")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.3")
    implementation("io.ktor:ktor-client-cio:3.0.3")

    implementation("cafe.adriel.voyager:voyager-navigator:${property("voyager.version")}")
    implementation("cafe.adriel.voyager:voyager-transitions:${property("voyager.version")}")
    implementation("cafe.adriel.voyager:voyager-koin:${property("voyager.version")}")

}
compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "GEstionProyectos"
            packageVersion = "1.0.0"
        }
    }
}
