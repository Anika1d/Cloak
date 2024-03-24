package com.clicklead.cloak.manager.impl

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.clicklead.cloak.datastore.dataStore
import com.clicklead.cloak.manager.api.ManagerDataStoreI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class ManagerDataStoreImpl(context: Context) : ManagerDataStoreI(context) {
   override val dataStore = context.dataStore
    companion object {
        val URL = stringPreferencesKey("url")
    }
    override suspend fun saveUrl(url: String) {
        dataStore.edit { preferences ->
            preferences[URL] = url
        }
    }
    private val urlFlow: Flow<String?> = dataStore.data.map { preferences ->
        preferences[URL]
    }.distinctUntilChanged()

    override suspend fun getUrl(): String? {
        return urlFlow.first()
    }

}