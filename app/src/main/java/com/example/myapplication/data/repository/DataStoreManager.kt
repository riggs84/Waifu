package com.example.myapplication.data.repository

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(private val ctx: Application) {

    private val defaultColumnsValue = 2

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("waifu_data_store")
    private val COLUMNS_COUNT = intPreferencesKey("columns_count")

    suspend fun setColumns(columns: Int) {
        ctx.dataStore.edit { preferences -> preferences[COLUMNS_COUNT] = columns }
    }

    fun getColumns(): Flow<Int> {
        return ctx.dataStore.data.map {
            it[COLUMNS_COUNT] ?: defaultColumnsValue
        }
    }
}