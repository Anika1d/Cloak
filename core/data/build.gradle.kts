plugins {
    id("cloak.kotlin-lib")
    alias(libs.plugins.kotlin.serialization)
}
dependencies {
    implementation(libs.kotlin.serialization.gen)
    implementation(libs.kotlin.serialization.json)
}