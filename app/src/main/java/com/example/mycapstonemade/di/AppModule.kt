package com.example.mycapstonemade.di

import com.example.core.domain.usecase.SportInteractor
import com.example.core.domain.usecase.SportUseCase
import com.example.mycapstonemade.detail.DetailSportViewModel
import com.example.mycapstonemade.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SportUseCase> {
        SportInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailSportViewModel(get()) }
}
