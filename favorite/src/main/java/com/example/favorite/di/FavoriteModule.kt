package com.example.favorite.di

import com.example.favorite.FavoriteSportViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteSportViewModel(get()) }
}