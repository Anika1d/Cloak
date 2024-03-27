plugins {
    id("cloak.android-compose")

}
android.namespace = "com.clicklead.cloak.singleactivity"

dependencies {
    implementation(libs.koin.androidx.compose)
    implementation(libs.kotlin.coroutines)
    implementation(libs.koin.core)
    implementation(libs.koin.core.coroutines)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.foundation)
    implementation(libs.compose.activity)
    implementation(libs.appsflyer)
    implementation(projects.core.usecase)
    implementation(libs.moko.mvvm.gen.compose)
    implementation(libs.moko.mvvm.livedata.compose)
    implementation(libs.moko.mvvm.flow.compose)
    implementation(libs.browser)
    implementation(projects.core.data)
    implementation(projects.datastore)
    implementation(libs.webkit)
}
