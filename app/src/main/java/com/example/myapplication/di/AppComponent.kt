package com.example.myapplication.di

import com.example.myapplication.ui.favorites.FavoritesFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.settings.SettingsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, DataBaseModule::class, DataStoreModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoritesFragment)
    fun inject(fragment: SettingsFragment)
}