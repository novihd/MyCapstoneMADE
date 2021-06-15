package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SportResponse (
    @field:SerializedName("idSport")
    var idSport: Int,

    @field:SerializedName("strSport")
    var strSport: String,

    @field:SerializedName("strFormat")
    var strFormat: String,

    @field:SerializedName("strSportThumb")
    var strSportThumb: String,

    @field:SerializedName("strSportDescription")
    var strSportDescription: String
    )