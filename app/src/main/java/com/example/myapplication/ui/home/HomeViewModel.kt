package com.example.myapplication.ui.home


import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.repository.IWaifuRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: IWaifuRepository) : ViewModel() {
}