package com.example.core.utils

import com.example.core.data.source.local.entity.SportEntity
import com.example.core.data.source.remote.response.SportResponse
import com.example.core.domain.model.Sport


object DataMapper {
    fun mapResponsesToEntities(input: List<SportResponse>): List<SportEntity> {
        val sportList = ArrayList<SportEntity>()
        input.map {
            val sport = SportEntity(
                idSport = it.idSport,
                strSport = it.strSport,
                strFormat = it.strFormat,
                strSportThumb = it.strSportThumb,
                strSportDescription = it.strSportDescription,
                isFavorite = false
            )
            sportList.add(sport)
        }
        return sportList
    }

    fun mapEntitiesToDomain(input: List<SportEntity>): List<Sport> =
        input.map {
            Sport(
                it.idSport,
                it.strSport,
                it.strFormat,
                it.strSportThumb,
                it.strSportDescription,
                it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Sport) = SportEntity(
        input.idSport,
        input.strSport,
        input.strFormat,
        input.strSportThumb,
        input.strSportDescription,
        input.isFavorite
    )
}