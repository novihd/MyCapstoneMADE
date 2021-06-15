package com.example.mycapstonemade.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Sport
import com.example.core.domain.usecase.SportUseCase

class DetailSportViewModel(private val sportUseCase: SportUseCase): ViewModel() {
    fun setFavoriteSport(sport: Sport, newStatus: Boolean) =
        sportUseCase.setFavoriteSport(sport, newStatus)
}