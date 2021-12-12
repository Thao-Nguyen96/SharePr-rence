package com.nxt.sharedpreferences2.respository

import android.content.Context
import androidx.datastore.core.DataStore
import java.util.prefs.Preferences

const val PREFERENCE_NAME = "preference_name"

class DataStoreRepository(context: Context) {

    private val dataStore : DataStore<Preferences> = context.crea
}