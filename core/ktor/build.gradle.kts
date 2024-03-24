plugins {
    id("cloak.kotlin-lib")
    alias(libs.plugins.kotlin.serialization) }

dependencies {
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.koin.ktor)
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)
}