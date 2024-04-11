package com.example.myapplication.di

import com.example.myapplication.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, DataBaseModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: HomeFragment)
}