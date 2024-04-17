package com.example.myapplication.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val ctx: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("waifu_data_store")
    private val COLUMNS_COUNT = intPreferencesKey("columns_count")

    suspend fun setColumns(columns: Int) {
        ctx.dataStore.edit { preferences -> preferences[COLUMNS_COUNT] = columns }
    }

    fun getColumns(): Flow<Int> {
        return ctx.dataStore.data.map {
            it[COLUMNS_COUNT] ?: 2
        }
    }
}