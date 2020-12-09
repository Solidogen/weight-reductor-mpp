package com.spyrdonapps.weightreductor.backend

import com.spyrdonapps.weightreductor.backend.deletelater.ShoppingListItem
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, 9090) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }
        routing {
            get("/") {
                call.respond(shoppingList)
            }
        }
    }.start(wait = true)
}

// this class doesn't resolve correctly in IDE, but it compiles fine within Gradle
private val shoppingList = mutableListOf(
    ShoppingListItem(0, "Cucumbers 🥒", 1),
    ShoppingListItem(1, "Tomatoes 🍅", 2),
    ShoppingListItem(2, "Orange Juice 🍊", 3)
)