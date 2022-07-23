package com.dedan.featureflaggingkmm

class HomeClass() {
    private val api=ApiClient()
    private val cacheSettings =CacheSettings()

    @Throws(Exception::class) suspend fun getAllSeasons(): List<SeasonsResponse> {
        return listOf(
            SeasonsResponse(seasonName = "Summer", contents = listOf(SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"),
                SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"),SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"))),
            SeasonsResponse(seasonName = "Winter", contents = listOf(SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"),
                SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"),SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"))),

                    SeasonsResponse(seasonName = "Autumn", contents = listOf(SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"),
                        SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"),SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"))),

        SeasonsResponse(seasonName = "Spring", contents = listOf(SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"),
            SeasonPhoto(url = "https://picsum.photos/id/1000/367/267"),SeasonPhoto(url = "https://picsum.photos/id/1000/367/267")))

        )
       // return api.getAllSeasons()
    }

    @Throws(Exception::class) suspend fun getFeatureFlag(): Boolean {
        val toggleFlag: Boolean? = api.getFeatureFlag()
        print("toggle flag value: $toggleFlag")
        return true
        return if(toggleFlag!=null){
            cacheSettings.cacheFeatureFlag(toggleFlag)
            toggleFlag
        }else {
            cacheSettings.featureFlag
        }
    }
}