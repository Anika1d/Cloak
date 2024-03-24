package com.clicklead.cloak.buildlogic

import org.gradle.api.Project

object ApkConfig {
    const val APPLICATION_ID = "com.clicklead.cloak"
    const val MIN_SDK_VERSION = 26
    const val TARGET_SDK_VERSION = 33
    const val COMPILE_SDK_VERSION = 34
    const val APPSFLYER_CODE_DEVELOPER: String = "YOUR_KEY"
    private const val DEBUG_VERSION = "DEBUG_VERSION"
    val Project.VERSION_CODE
        get() = prop(key = "version_code", default = 1).toInt()
    val Project.VERSION_NAME
        get() = prop(key = "version_name", default = DEBUG_VERSION)
}

internal fun Project.prop(key: String, default: Any): String {
    return providers.gradleProperty(key).getOrElse(default.toString())
}
