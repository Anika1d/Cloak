package com.clicklead.cloak.manager.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.clicklead.cloak.datastore.dataStore

abstract class ManagerDataStoreI(private val context: Context) {
    open val dataStore: DataStore<Preferences>
        get() = context.dataStore
    abstract suspend fun getUrl(): String?
    abstract suspend fun saveUrl(url: String)
}