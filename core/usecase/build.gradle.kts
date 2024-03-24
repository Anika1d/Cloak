plugins {
    id("cloak.kotlin-lib")
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.kotlin.coroutines)
    implementation(libs.koin.core.coroutines)
    implementation(projects.cloak.core.repository)
    implementation(projects.cloak.core.data)
}