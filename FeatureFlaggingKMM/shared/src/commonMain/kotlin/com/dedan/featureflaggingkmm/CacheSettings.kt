package com.dedan.featureflaggingkmm

import com.russhwolf.settings.Settings
import com.russhwolf.settings.boolean
import kotlin.reflect.KProperty

class CacheSettings() {
    private val settings: Settings = Settings()

    fun checkKeyExists(key:String):Boolean{
        return  settings.hasKey(key)
    }

    fun cacheFeatureFlag(flag: Boolean){
        settings.putBoolean(CacheKeys.featureKey,flag)
    }

    var featureFlag by settings.boolean(CacheKeys.featureKey,defaultValue = false)

}

object CacheKeys{
    const val featureKey:String ="feature_flag"
}
