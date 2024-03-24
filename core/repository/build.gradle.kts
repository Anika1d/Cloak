plugins {
    id("cloak.kotlin-lib")
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.ktor.client.android)
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)
    implementation(projects.cloak.core.service)
    implementation(projects.cloak.core.data)
}