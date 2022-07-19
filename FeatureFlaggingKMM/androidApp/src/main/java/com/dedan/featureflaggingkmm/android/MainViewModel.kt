package com.dedan.featureflaggingkmm.android

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dedan.featureflaggingkmm.HomeClass
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val mainScope = MainScope()
    private val kmmSdk= HomeClass()
    var mainState by mutableStateOf<MainState>(MainState())

    init {
        getAllSeasons()
    }

    private fun getAllSeasons() {
        mainScope.launch {
            mainState=mainState.copy(isLoading = true)
            kotlin.runCatching {
                kmmSdk.getAllSeasons()
            }.onSuccess {
                mainState= mainState.copy(isLoading = false, allSeasons = it, loadError = null)
            }.onFailure {
                mainState=mainState.copy(isLoading = false, loadError = it.message)
            }
        }
    }

    fun cleanUp(){
        mainScope.cancel()
    }
}

class FeatureViewModel: ViewModel(){
    private val mainScope = MainScope()
    private val kmmSdk= HomeClass()
    var isLoading by mutableStateOf<Boolean>(true)
    var featureToggle by mutableStateOf<Boolean>(false)

    init {
        getFeatureFlag()
    }

    private fun getFeatureFlag() {
        mainScope.launch {
            isLoading=true
            kotlin.runCatching {
                kmmSdk.getFeatureFlag()
            }.onSuccess {
                featureToggle=it
                isLoading=false
            }.onFailure {
                isLoading=false
            }
        }
    }
}