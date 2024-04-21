package com.example.myapplication.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.db.WaifuEntity
import com.example.myapplication.data.repository.DataStoreManager
import com.example.myapplication.domain.repository.IWaifuDataBaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val dataBase: IWaifuDataBaseRepository,
) : ViewModel() {

    private val mutableViewState = MutableLiveData<List<WaifuEntity>>()
    val viewState: LiveData<List<WaifuEntity>> = mutableViewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataBase.getAllFavorites().collect {
                mutableViewState.postValue(it)
            }
        }
    }

    fun markAsFavorite(pos: Int) {
        val entry = mutableViewState.value?.get(pos)
        entry?.let {
            it.isFavorite = !it.isFavorite
            it.setFavoriteIcon()

            viewModelScope.launch(Dispatchers.IO) {
                dataBase.updateEntry(entry)
                dataBase.getAllFavorites().collect {
                    mutableViewState.postValue(it)
                }
            }
        }
    }
}