package com.dedan.featureflaggingkmm.android

import com.dedan.featureflaggingkmm.SeasonNames
import com.dedan.featureflaggingkmm.SeasonPhoto
import com.dedan.featureflaggingkmm.SeasonsResponse



data class MainState(
    val allSeasons: List<SeasonsResponse> = emptyList(),
    val isLoading: Boolean = false,
    val loadError: String? = null,
) {
    val winterPhotos: List<SeasonPhoto>
        get() = sortSeasons(SeasonNames.Winter.name)
    val summerPhotos: List<SeasonPhoto>
        get() = sortSeasons(SeasonNames.Summer.name)
    val autumnPhotos: List<SeasonPhoto>
        get() = sortSeasons(SeasonNames.Autumn.name)
    val springPhotos: List<SeasonPhoto>
        get() = sortSeasons(SeasonNames.Spring.name)

    private fun sortSeasons(name:String): List<SeasonPhoto> {
        return allSeasons.find {
            it.seasonName==name
        }?.contents ?: emptyList()
        }

}