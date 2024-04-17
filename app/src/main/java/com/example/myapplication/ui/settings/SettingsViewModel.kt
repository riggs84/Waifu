package com.example.myapplication.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.DataStoreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val mutableViewState = MutableLiveData<Int>()
    val viewState: LiveData<Int> = mutableViewState

    init {
        viewModelScope.launch {
            dataStoreRepository.getColumns().collect {
                mutableViewState.postValue(it)
            }
        }
    }

    fun setColumns(columns: Int) {
        viewModelScope.launch {
            dataStoreRepository.setColumns(columns).also {
                dataStoreRepository.getColumns().collect {
                    mutableViewState.postValue(it)
                }
            }
        }
    }
}