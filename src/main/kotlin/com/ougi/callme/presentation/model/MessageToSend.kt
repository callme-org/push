package com.ougi.callme.presentation.model

import com.ougi.callme.domain.model.Message
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MessageToSend(
    @SerialName("token")
    val token: String,
    @SerialName("messageData")
    val messageData: Message
)