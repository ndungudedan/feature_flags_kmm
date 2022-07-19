package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

    fun Route.seasonsRouting() {
        route("/seasons") {

            get {
                if (seasons.isNotEmpty()) {
                    call.respond(seasons)
                } else {
                    call.respondText("No seasons found", status = HttpStatusCode.OK)
                }
            }

        }

    }

fun Route.featureFlagRouting(){

    route("/featureFlag"){
        get {
            call.respond("toggleFeature")
        }

        post {
            val toggleFlag=call.receive<FeatureFlagRequest>()
            toggleFeature=toggleFlag.toggle
            call.respondText("Feature Flagging is now $toggleFeature", status = HttpStatusCode.Created)
        }

    }
}
