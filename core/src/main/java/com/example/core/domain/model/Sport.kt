package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sport(
    val idSport: Int,
    val strSport: String,
    val strFormat: String,
    val strSportThumb: String,
    val strSportDescription: String,
    val isFavorite: Boolean
): Parcelable