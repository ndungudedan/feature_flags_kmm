package com.dedan.featureflaggingkmm

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


class ApiClient {

    //Example URL
    //val baseUrl = "http://192.168.2.105:8080/"
    val baseUrl = "http://your-ip-address:server-port/"

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun getAllSeasons(): List<SeasonsResponse> {
        try {
            return client.get(baseUrl + "seasons").body()
        }catch (e: Exception){
            print("seasons API FAILED: "+e.message )
        }
        return emptyList()
    }
}
