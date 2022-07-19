package com.dedan.featureflaggingkmm

class HomeClass() {
    private val api=ApiClient()
    private val cacheSettings =CacheSettings()

    @Throws(Exception::class) suspend fun getAllSeasons(): List<SeasonsResponse> {
        return api.getAllSeasons()
    }

    @Throws(Exception::class) suspend fun getFeatureFlag(): Boolean {
        val toggleFlag: Boolean? = api.getFeatureFlag()
        print("toggle flag value: $toggleFlag")
        return if(toggleFlag!=null){
            cacheSettings.cacheFeatureFlag(toggleFlag)
            toggleFlag
        }else {
            cacheSettings.featureFlag
        }
    }
}