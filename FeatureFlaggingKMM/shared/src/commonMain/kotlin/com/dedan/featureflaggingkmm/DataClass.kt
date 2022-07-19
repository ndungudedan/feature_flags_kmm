package com.dedan.featureflaggingkmm

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


enum class SeasonNames{
    Winter,Summer,Autumn,Spring
}

@Serializable
data class SeasonsResponse(
    @SerialName("name") val seasonName:String,
    @SerialName("contents") val contents : List<SeasonPhoto>,
)

@Serializable
data class SeasonPhoto (
    @SerialName("url") val url : String = "",
    @SerialName("quote") val quote : String = "",
)