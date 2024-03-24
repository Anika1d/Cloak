plugins {
    id("cloak.android-lib")
    alias(libs.plugins.kotlin.serialization)
}
android.namespace = "com.clicklead.cloak.datastore"

dependencies {
    implementation(libs.datastore)
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)
}