package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.ItemDto
import com.example.myapplication.domain.repository.IWaifuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: IWaifuRepository) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _viewState.value = ViewState.Loading
                val response = withContext(Dispatchers.IO) {
                    repository.getWaifuData()
                }

                if (!response.isSuccessful) {
                    throw Exception(response.errorBody().toString())
                }

                //_viewState.value = DataResult.Success()
            } catch (e: Exception) {
                _viewState.postValue(ViewState.Error(e.message ?: "Error happen: ${e.stackTrace}"))
            }
        }
    }
}

sealed class ViewState {
    data class Success(val data: List<ItemDto>) : ViewState()
    data class Error(val errMsg: String) : ViewState()
    data object Loading : ViewState()
}