package com.example.myapplication.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.DataStoreManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val mutableViewState = MutableLiveData<Int>()
    val viewState: LiveData<Int> = mutableViewState

    init {
        viewModelScope.launch {
            dataStoreManager.getColumns().collect {
                mutableViewState.postValue(it)
            }
        }
    }

    fun setColumns(columns: Int) {
        viewModelScope.launch {
            dataStoreManager.setColumns(columns)
        }
    }
}