plugins {
    id("cloak.kotlin-lib")
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.kotlin.coroutines)
    implementation(projects.cloak.core.data)
    implementation(projects.cloak.core.ktor)
}