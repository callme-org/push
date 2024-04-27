package com.ougi.callme.presentation.routing

import com.ougi.callme.domain.model.SendResult
import com.ougi.callme.domain.usecase.SendPushUseCase
import com.ougi.callme.presentation.model.MessageToSend
import com.ougi.callme.presentation.model.MessageWithDataToSend
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {
        route("/m") {
            route("/push") {
                sendPushWithData()
                sendPushWithMultipleData()
            }
        }
    }
}

private fun Route.sendPushWithData() {
    val sendPushUseCase by inject<SendPushUseCase>()
    route("/send") {
        handle {
            val messageToSend = call.receive<MessageToSend>()
            sendPushUseCase.sendMessage(
                token = messageToSend.token,
                messageData = listOf(messageToSend.messageData),
            )
        }

    }
}

private fun Route.sendPushWithMultipleData() {
    val sendPushUseCase by inject<SendPushUseCase>()
    route("/sendWithData") {
        handle {
            val messageToSend = call.receive<MessageWithDataToSend>()
            sendPushUseCase.sendMessage(
                token = messageToSend.token,
                messageData = messageToSend.messageData,
            ).run { respondPushStatus(this) }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.respondPushStatus(result: SendResult) =
    call.respond(
        when (result) {
            SendResult.Success -> HttpStatusCode.OK
            SendResult.Failed.Unknown -> HttpStatusCode.BadRequest
        }
    )
