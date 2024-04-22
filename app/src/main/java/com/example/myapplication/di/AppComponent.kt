package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.ui.favorites.FavoritesFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.settings.SettingsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApiModule::class, DataBaseModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoritesFragment)
    fun inject(fragment: SettingsFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(app: Application): Builder
        fun build(): AppComponent
    }
}