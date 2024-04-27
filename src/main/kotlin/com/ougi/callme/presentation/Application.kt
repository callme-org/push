package com.ougi.callme.presentation

import com.ougi.callme.di.appModule
import com.ougi.callme.presentation.firebase.FirebaseInitializer
import com.ougi.callme.presentation.routing.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module
    )
        .start(wait = true)
}

fun Application.module() {
    installPlugins()
    configureFirebase()
    configureRouting()
}

private fun Application.installPlugins() {
    install(Koin) {
        modules(appModule)
    }
}

fun Application.configureFirebase() {
    val firebaseInitializer by inject<FirebaseInitializer>()
    firebaseInitializer.initialize()
}
