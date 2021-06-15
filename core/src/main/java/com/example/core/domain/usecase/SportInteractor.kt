package com.example.core.domain.usecase

import com.example.core.data.Resource
import com.example.core.domain.model.Sport
import com.example.core.domain.repository.ISportRepository
import kotlinx.coroutines.flow.Flow

class SportInteractor(private val sportRepository: ISportRepository): SportUseCase {
    override fun getAllSport(): Flow<Resource<List<Sport>>> = sportRepository.getAllSport()

    override fun getFavoriteSport(): Flow<List<Sport>> = sportRepository.getFavoriteSport()

    override fun setFavoriteSport(sport: Sport, state: Boolean) = sportRepository.setFavoriteSport(sport, state)

}