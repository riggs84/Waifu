package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.WaifuEntity
import com.example.myapplication.domain.repository.IWaifuDataBaseRepository
import com.example.myapplication.domain.repository.IWaifuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val networkRepository: IWaifuRepository,
    private val dataBaseRepository: IWaifuDataBaseRepository
) : ViewModel() {

    private val mutableViewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = mutableViewState

    init {
        getData()
    }

    fun markAsFavorite(pos: Long) {
        val entry = (mutableViewState.value as ViewState.Success).data[pos.toInt() - 1]
        entry.isFavorite = true
        entry.setFavoriteIcon()
        viewModelScope.launch(Dispatchers.IO) {
            dataBaseRepository.updateEntry(entry)
            dataBaseRepository.getAll()
                .collect { items -> mutableViewState.postValue(ViewState.Success(items)) }
        }
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.Main) {
            mutableViewState.value = ViewState.Loading

            try {
                withContext(Dispatchers.IO) {
                    val networkResponse = networkRepository.getWaifuData()

                    if (!networkResponse.isSuccessful || networkResponse.body() == null) {
                        throw Exception(networkResponse.errorBody().toString())
                    }

                    // checking for non null is provided
                    val list = networkResponse.body()!!.files.map {
                        WaifuEntity(url = it)
                    }.toList()

                    dataBaseRepository.insertList(list)
                }
            } catch (e: Exception) {
                mutableViewState.postValue(
                    ViewState.Error(
                        e.message ?: "Error happen: ${e.stackTrace}"
                    )
                )
            }
            dataBaseRepository.getAll()
                .collect { items -> mutableViewState.postValue(ViewState.Success(items)) }
        }
    }
}

sealed class ViewState {
    data class Success(val data: List<WaifuEntity>) : ViewState()
    data class Error(val errMsg: String) : ViewState()
    data object Loading : ViewState()
}