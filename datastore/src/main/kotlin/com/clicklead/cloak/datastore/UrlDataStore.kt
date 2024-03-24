package com.clicklead.cloak.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "cloak_datastore_url_81")
