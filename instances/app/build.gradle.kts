plugins {
    id("cloak.android-app")
}

android.namespace = "com.clicklead.cloak"

dependencies {
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.appsflyer)
    implementation(libs.koin.androidx.compose)
    implementation(projects.components.singleactivity)
    implementation(libs.slf4j) //else Missing classes detected while running R8.
}

